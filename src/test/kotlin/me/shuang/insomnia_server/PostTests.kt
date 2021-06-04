package me.shuang.insomnia_server

import me.shuang.insomnia_server.repository.PostRepository
import me.shuang.insomnia_server.utils.json
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostTests {

    val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var postRepository: PostRepository

    @Test
    fun testPostOutput() {
        logger.info { json(postRepository.findAll()) }
    }
}
