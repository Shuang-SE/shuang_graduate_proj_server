package me.shuang.insomnia_server.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

/**
 * @author 11206
 * @date 2021-05-06
 */
@RestController
class UserLoginController {

    @RequestMapping(value = ["/checkLogin"], method = [GET])
    fun checkLogin() = true
}
