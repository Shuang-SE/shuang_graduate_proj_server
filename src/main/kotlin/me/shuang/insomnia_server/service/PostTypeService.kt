package me.shuang.insomnia_server.service

import me.shuang.insomnia_server.repository.PostTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author shuang
 * @date 2021/6/3
 */
@Service
class PostTypeService {

    @Autowired
    private lateinit var postTypeRepository: PostTypeRepository

    fun getAll() = postTypeRepository.findAll()
}
