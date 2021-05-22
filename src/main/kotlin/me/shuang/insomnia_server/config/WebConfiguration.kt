package me.shuang.insomnia_server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


/**
 * @author shuang
 * @date 2021-05-06
 */
@Configuration
class WebConfiguration {

//    @Bean
//    fun corsOriginConfigurer(): WebMvcConfigurer = object : WebMvcConfigurer {
//        override fun addCorsMappings(registry: CorsRegistry) {
//            registry.addMapping("/**")
//        }
//    }

//    private fun corsConfig(): CorsConfiguration = CorsConfiguration().apply {
//        addAllowedOrigin("*")
//        addAllowedHeader("*")
//        addAllowedMethod("*")
//        allowCredentials = true
//        maxAge = 3600L
//    }
//
//    @Bean
//    fun corsFilter(): CorsFilter {
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", corsConfig())
//        return CorsFilter(source)
//    }
}
