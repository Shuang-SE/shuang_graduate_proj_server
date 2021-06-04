package me.shuang.insomnia_server.controller.common

import me.shuang.insomnia_server.bean.PostType
import me.shuang.insomnia_server.service.PostTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

/**
 * @author shuang
 * @date 2021/6/3
 */
@RestController
class PostTypeController {

    @Autowired
    private lateinit var postTypeService: PostTypeService

    @RequestMapping(value = ["/open/post_type/all"], method = [GET])
    fun getAll(): MutableList<PostType> = postTypeService.getAll()

}
