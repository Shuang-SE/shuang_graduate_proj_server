package me.shuang.insomnia_server.config

import me.shuang.insomnia_server.filter.AuthTokenInterceptor
import me.shuang.insomnia_server.filter.CrossOriginInterceptor
import me.shuang.insomnia_server.filter.JsonInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

/**
 * @author 11206
 * @date 2021-05-06
 */
@Configuration
class InterceptorInjectConfiguration : WebMvcConfigurationSupport() {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthTokenInterceptor()).addPathPatterns(listOf("/**"))
        registry.addInterceptor(CrossOriginInterceptor()).addPathPatterns(listOf("/**"))
        registry.addInterceptor(JsonInterceptor()).addPathPatterns(listOf("/**"))
        super.addInterceptors(registry)
    }
}
