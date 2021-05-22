package me.shuang.insomnia_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InsomniaServerApplication

fun main(args: Array<String>) {
	runApplication<InsomniaServerApplication>(*args)
}
