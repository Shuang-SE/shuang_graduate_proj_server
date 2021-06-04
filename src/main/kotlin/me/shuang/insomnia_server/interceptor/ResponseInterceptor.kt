package me.shuang.insomnia_server.interceptor

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import java.nio.charset.StandardCharsets
import me.shuang.insomnia_server.utils.error
import me.shuang.insomnia_server.utils.json
import me.shuang.insomnia_server.utils.model.Response
import me.shuang.insomnia_server.utils.success
import java.awt.image.BufferedImage

@ControllerAdvice
class ResponseInterceptor : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>) = true

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        val resp = (response as ServletServerHttpResponse).servletResponse
        body?.let {
            return when (body) {
                is BufferedImage -> body
                !is Response<*> -> {
                    when (body) {
                        is Exception -> error(body.message)
                        else -> {
                            resp.contentType = "application/json;charset=UTF-8"
                            resp.characterEncoding = StandardCharsets.UTF_8.name()
                            success(body)
                        }
                    }
                }
                else -> body
            }
        }
        return null
    }
}
