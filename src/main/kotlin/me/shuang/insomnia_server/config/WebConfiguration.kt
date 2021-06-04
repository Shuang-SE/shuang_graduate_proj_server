package me.shuang.insomnia_server.config

import me.shuang.insomnia_server.interceptor.CrossOriginInterceptor
import me.shuang.insomnia_server.interceptor.TokenInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * @author shuang
 * @date 2021-05-06
 */
private const val PATH_ALL = "/**"
private const val PATH_LOGIN = "/login/**"
private const val PATH_REGISTER = "/register/**"
private const val PATH_TEST = "/test/**"
private const val PATH_OPEN = "/open/**"
private const val PATH_ERROR = "/error"

@Configuration
class WebConfiguration : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(CrossOriginInterceptor()).addPathPatterns(listOf(PATH_ALL))
        registry.addInterceptor(TokenInterceptor()).addPathPatterns(listOf(PATH_ALL))
            .excludePathPatterns(listOf(PATH_LOGIN, PATH_REGISTER, PATH_TEST, PATH_OPEN, PATH_ERROR))
        super.addInterceptors(registry)
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(0, MappingJackson2HttpMessageConverter())
        converters.add(BufferedImageHttpMessageConverter())
    }
}
