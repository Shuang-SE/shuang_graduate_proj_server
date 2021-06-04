package me.shuang.insomnia_server.service

import me.shuang.insomnia_server.bean.RequiringPost
import me.shuang.insomnia_server.repository.RequiringPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

/**
 * @author shuang
 * @date 2021/6/4
 */
@Service
class RequiringPostService {
    @Autowired
    private lateinit var requiringPostRepository: RequiringPostRepository

    fun getByCandidateId(id: Int) =
        requiringPostRepository.getRequiringPostsByCandidateIdOrderByCreateTimeDesc(id)

    fun getByPostId(id: Int) =
        requiringPostRepository.getRequiringPostsByPostIdOrderByCreateTimeDesc(id)

    fun getByHRId(id: Int) =
        requiringPostRepository.getRequiringPostsByRefPost_HumanResourceIdOrderByCreateTimeDesc(id)

    fun applyPost(require: RequiringPost): Boolean =
        requiringPostRepository.insertOne(require.candidateId!!, require.postId!!) > 0

    fun deletePost(id: Int) = requiringPostRepository.deleteById(id)

    fun exists(require: RequiringPost): Boolean =
        requiringPostRepository.existsByCandidateIdAndPostId(require.candidateId!!, require.postId!!)
}
