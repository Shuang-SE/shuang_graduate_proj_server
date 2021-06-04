package me.shuang.insomnia_server.interceptor

import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.Claim
import me.shuang.insomnia_server.utils.JwtUtils
import me.shuang.insomnia_server.utils.RedisUtils
import me.shuang.insomnia_server.utils.errorToJson
import org.apache.logging.log4j.util.Strings
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class TokenInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        private lateinit var context: ApplicationContext

        fun setContext(context: ApplicationContext) {
            this.context = context
        }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization")
        return if (Strings.isNotEmpty(token)) {
            val redisUtils = context.getBean(RedisUtils::class.java)
            try {
                val verifyToken = JwtUtils().verifyToken(token)
                val userId = verifyToken?.getClaim("userId")?.asInt()
                val userType = verifyToken?.getClaim("userType")?.asString()
                val tokenInRedis: String = redisUtils.getUserTokenInHash("${userType}_${userId}")
                if (Strings.isEmpty(tokenInRedis) || token == tokenInRedis) {
                    super.preHandle(request, response, handler)
                } else {
                    throw JWTVerificationException("")
                }
            } catch (e: JWTVerificationException) {
                logger.error(e.message)
                error(response, "您的登录信息无效或已过期，请重新登录")
                false
            }
        } else {
            error(response, "您尚未登录，请登录后重试")
            false
        }
    }

    private fun error(response: HttpServletResponse, msg: String) {
        response.characterEncoding = "utf-8"
        response.setHeader("Content-Type", "application/json")
        try {
            val writer = response.writer
            writer.write(errorToJson(msg))
            writer.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
