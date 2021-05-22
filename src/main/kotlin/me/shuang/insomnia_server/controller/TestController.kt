package me.shuang.insomnia_server.controller

import me.shuang.insomnia_server.utils.json
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

/**
 * @author 11206
 * @date 2021-05-06
 */
@CrossOrigin
@RestController
class TestController {

    @RequestMapping(value = ["test001/{param}"], method = [GET], )
    fun test001(@PathVariable param: String): String {
        val map = mutableMapOf(
            "param" to param
        )
        return json(map)
    }

}
