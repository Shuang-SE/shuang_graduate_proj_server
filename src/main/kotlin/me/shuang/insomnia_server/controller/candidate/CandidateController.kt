package me.shuang.insomnia_server.controller.candidate

import me.shuang.insomnia_server.bean.Candidate
import me.shuang.insomnia_server.service.CandidateService
import me.shuang.insomnia_server.utils.getUserIdFromJwt
import me.shuang.insomnia_server.utils.model.ResponseContent
import me.shuang.insomnia_server.utils.model.TokenBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class CandidateController {

    @Autowired
    private lateinit var candidateService: CandidateService

    @RequestMapping(value = ["/test/test_get_candidates"], method = [GET])
    fun getAll(): List<Candidate> = candidateService.getAll()

    @RequestMapping(value = ["/register/candidate"], method = [POST])
    fun register(@RequestBody candidate: Candidate): Any = try {
        TokenBean(candidateService.register(candidate))
    } catch (e: Exception) {
        error(e)
    }

    @RequestMapping(value = ["/login/candidate"], method = [POST])
    fun login(@RequestBody candidate: Candidate): Any? {
        return TokenBean(candidateService.login(candidate))
    }

    @RequestMapping(value = ["/open/user_info/{id}"], method = [GET])
    fun getUserInfo(@PathVariable id: Int): Candidate {
        return candidateService.getOne(id)
    }

    @RequestMapping(value = ["open/self_info"], method = [GET])
    fun getSelfInfo(@RequestHeader(value = "Authorization") token: String): Candidate {
        val userId = getUserIdFromJwt(token)
        return candidateService.getOne(userId)
    }
}
