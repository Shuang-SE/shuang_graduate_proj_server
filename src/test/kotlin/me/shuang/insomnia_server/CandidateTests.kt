package me.shuang.insomnia_server

import me.shuang.insomnia_server.bean.Candidate
import me.shuang.insomnia_server.repository.CandidateRepository
import me.shuang.insomnia_server.service.CandidateService
import me.shuang.insomnia_server.utils.RedisUtils
import me.shuang.insomnia_server.utils.md5Encrypt
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.logging.Logger

@SpringBootTest
class CandidateTests {
    val logger = Logger.getLogger(this::class.qualifiedName)

    @Autowired
    private lateinit var candidateService: CandidateService

    @Autowired
    private lateinit var candidateRepository: CandidateRepository

    @Autowired
    private lateinit var redisUtils: RedisUtils

    @Test
    fun testRegister() {
        candidateRepository.save(Candidate().apply {
            username = "adam"
            password = md5Encrypt("god")
            name = "eva"
            email = "candidate@example.com"
            avatarUrl = "default"
            type = "student"
        })
    }

    @Test
    fun testLogin() {
        logger.info(candidateService.login(username = "adam", password = "god"))
    }

    @Test
    fun testRedis() {
        redisUtils.template.opsForHash<String, Any>().put("hello", "1", "12");
    }
}
