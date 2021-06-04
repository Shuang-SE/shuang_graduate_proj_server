package me.shuang.insomnia_server

import me.shuang.insomnia_server.interceptor.TokenInterceptor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class InsomniaServerApplication

fun main(args: Array<String>) {
	val runApplication = runApplication<InsomniaServerApplication>(*args) as ApplicationContext
	TokenInterceptor.setContext(runApplication)
}
