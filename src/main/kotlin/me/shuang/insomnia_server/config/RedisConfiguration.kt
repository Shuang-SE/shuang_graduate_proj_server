package me.shuang.insomnia_server.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import java.net.UnknownHostException

@Configuration
class RedisConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = ["redisTemplate"])
    @Throws(UnknownHostException::class)
    fun redisTemplate(
        factory: RedisConnectionFactory?
    ): RedisTemplate<String, Any>? {
        val serializer = Jackson2JsonRedisSerializer(Any::class.java)
        serializer.setObjectMapper(ObjectMapper().apply {
            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL)
        })
        return RedisTemplate<String, Any>().apply {
            setConnectionFactory(factory!!)
            keySerializer = serializer
            valueSerializer = serializer
            hashKeySerializer = serializer
            hashValueSerializer = serializer
            afterPropertiesSet()
        }
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate::class)
    @Throws(UnknownHostException::class)
    fun stringRedisTemplate(
        factory: RedisConnectionFactory?
    ): StringRedisTemplate? {
        return StringRedisTemplate().apply {
            setConnectionFactory(factory!!)
        }
    }
}
