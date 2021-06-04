package me.shuang.insomnia_server.interceptor

import org.apache.logging.log4j.util.Strings
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author 11206
 * @date 2021-05-06
 */
class CrossOriginInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        response.setHeader("x-frame-options", "SAMEORIGIN");

        val origin = request.getHeader("Origin")
        if (Strings.isNotBlank(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin)
        }
        // 自适应所有自定义头
        val headers = request.getHeader("Access-Control-Request-Headers")
        if (Strings.isNotBlank(headers)) {
            response.setHeader("Access-Control-Allow-Headers", headers)
            response.setHeader("Access-Control-Expose-Headers", headers)
        }
        // 允许跨域的请求方法类型
        response.setHeader("Access-Control-Allow-Methods", "*")
        // 预检命令（OPTIONS）缓存时间，单位：秒
        response.setHeader("Access-Control-Max-Age", "3600")
        // 明确许可客户端发送Cookie，不允许删除字段即可
        response.setHeader("Access-Control-Allow-Credentials", "true")

        response.contentType = "application/json;charset=UTF-8"
        response.characterEncoding = "UTF-8"

        return super.preHandle(request, response, handler)
    }
}
