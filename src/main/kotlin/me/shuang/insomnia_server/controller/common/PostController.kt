package me.shuang.insomnia_server.controller.common

import me.shuang.insomnia_server.service.PostService
import me.shuang.insomnia_server.utils.paginate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

/**
 * @author shuang
 * @date 2021/6/2
 */
@RestController
class PostController {

    @Autowired
    private lateinit var postService: PostService

    @RequestMapping(value = ["/open/posts/{page}/{pageSize}/{type}"], method = [GET])
    fun getPostList(
        @PathVariable page: Int,
        @PathVariable pageSize: Int,
        @PathVariable type: String
    ): Map<String, Any> {
        if (type == "all") {
            return paginate(postService.getPostList(page, pageSize))
        }
        return paginate(postService.getPostListByPostTypeId(type.toInt(), page, pageSize))
    }

}
