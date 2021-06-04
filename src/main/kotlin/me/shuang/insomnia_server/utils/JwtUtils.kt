package me.shuang.insomnia_server.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

private const val KEY_ALGORITHM = "RSA"
private const val KEY_INIT_SIZE = 2048

@Volatile
private var keyPairGenerator: KeyPairGenerator? = null

@Volatile
private var keyPair: KeyPair? = null

@Component
class JwtUtils {

    @Autowired
    private lateinit var redisUtils: RedisUtils

    /**
     * 获取验证算法需要的密钥
     *
     * @return 密钥
     * @throws NoSuchAlgorithmException
     */
    @Throws(NoSuchAlgorithmException::class)
    private fun getKeyPair(): KeyPair? {
        // 单例模式, 双重锁检验, 在保证正确性的同时提高程序运行的效率 => (volatile & synchronized)
        if (keyPairGenerator == null) {
            // 类锁, 保证程序运行时只有一对密钥
            synchronized(KeyPairGenerator::class.java) {
                if (keyPairGenerator == null) {
                    // 初始化
                    keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM)
                    keyPairGenerator!!.initialize(KEY_INIT_SIZE)
                    // 生成密钥对
                    keyPair = keyPairGenerator!!.genKeyPair()
                }
            }
        }
        return keyPair
    }

    /**
     * 获取生成和验证token所需要的算法
     *
     * @return algorithm
     */
    private fun getAlgorithm(): Algorithm? {
        try {
            val keyPair = getKeyPair()
            return Algorithm.RSA256(keyPair!!.public as RSAPublicKey, keyPair.private as RSAPrivateKey)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    fun generateToken(userId: Int?, userType: String = "candidate"): String? {
        try {
            val tokenString = JWT.create()
                .withExpiresAt(DateTime.now().plusMonths(1).toDate())
                .withClaim("userType", userType)
                .withClaim("userId", userId)
                .sign(getAlgorithm())
            redisUtils.putUserTokenIntoHash("${userType}_${userId}", tokenString)
            return tokenString
        } catch (e: JWTCreationException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 验证token
     *
     * @param tokenString
     * @return
     */
    @Throws(JWTVerificationException::class)
    fun verifyToken(tokenString: String?): DecodedJWT? {
        val verifier = JWT.require(Objects.requireNonNull(getAlgorithm())).build()
        return verifier.verify(tokenString)
    }
}

fun getUserIdFromJwt(token: String) = JwtUtils().verifyToken(token)?.getClaim("userId")?.asInt() ?: -1

