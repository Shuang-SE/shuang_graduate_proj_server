package me.shuang.insomnia_server.utils

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

private const val KEY_REDIS_USER_TOKEN_HASH = "user_token_hash"

@Component
class RedisUtils {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var stringRedisTemplate: StringRedisTemplate

    lateinit var template: RedisTemplate<String, Any>

    lateinit var stringTemplate: StringRedisTemplate

    @PostConstruct
    fun fetchTemplate() {
        template = redisTemplate
        stringTemplate = stringRedisTemplate
        logger.info("redis init completed.")
    }


    fun putUserTokenIntoHash(userId: String, tokenString: String) {
        redisTemplate.opsForHash<String, Any>().put(KEY_REDIS_USER_TOKEN_HASH, userId, tokenString)
    }

    fun getUserTokenInHash(userId: String?): String {
        return redisTemplate.opsForHash<String, Any>().get(KEY_REDIS_USER_TOKEN_HASH, userId!!) as String
    }

    fun getRedisTemplate() = redisTemplate

    fun getStringRedisTemplate() = stringRedisTemplate

}
