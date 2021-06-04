package me.shuang.insomnia_server.service

import me.shuang.insomnia_server.bean.HumanResource
import me.shuang.insomnia_server.repository.HRRepository
import me.shuang.insomnia_server.repository.PostRepository
import me.shuang.insomnia_server.utils.JwtUtils
import me.shuang.insomnia_server.utils.md5Encrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author shuang
 * @date 2021/6/3
 */
@Service
class HRService {

    @Autowired
    private lateinit var hrRepository: HRRepository

    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    fun getAllByCompanyId(companyId: Int): MutableList<HumanResource> =
        hrRepository.getHumanResourcesByCompanyIdOrderByCreateTimeDesc(companyId)

    fun getById(id: Int): HashMap<String, Any> {
        return hashMapOf(
            "hr" to hrRepository.getOne(id),
            "posts" to postRepository.getPostsByHumanResourceIdOrderByCreateTimeDesc(id)
        )
    }

    fun register(hr: HumanResource): String? {
        if (!hrRepository.existsByUsernameOrEmail(hr.username, hr.email)) {
            hr.avatarUrl = "default"
            hr.password = md5Encrypt(hr.password)
            hrRepository.save(hr)
            return login(hr)
        }
        return null
    }

    private fun login(hr: HumanResource): String? {
        hrRepository.checkLogin(hr.username, md5Encrypt(hr.password))?.let {
            return jwtUtils.generateToken(it.id, "hr")
        }
        return null
    }


}
