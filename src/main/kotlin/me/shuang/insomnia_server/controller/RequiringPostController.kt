package me.shuang.insomnia_server.controller

import me.shuang.insomnia_server.bean.RequiringPost
import me.shuang.insomnia_server.service.RequiringPostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST

/**
 * @author shuang
 * @date 2021/6/4
 */
@RestController
class RequiringPostController {

    @Autowired
    private lateinit var requiringPostService: RequiringPostService

    @RequestMapping(value = ["/post/applying/candidate/{id}"], method = [GET])
    fun getByCandidateId(@PathVariable id: Int) = requiringPostService.getByCandidateId(id)

    @RequestMapping(value = ["/post/applying/hr/{id}"], method = [GET])
    fun getByHRId(@PathVariable id: Int) = requiringPostService.getByHRId(id)

    @RequestMapping(value = ["/post/applying/post/{id}"], method = [GET])
    fun getByPostId(@PathVariable id: Int) = requiringPostService.getByPostId(id)

    @RequestMapping(value = ["/post/apply"], method = [POST])
    fun applyPost(@RequestBody require: RequiringPost): Boolean =
        if (requiringPostService.exists(require)) {
            false
        } else {
            requiringPostService.applyPost(require)
        }

}
