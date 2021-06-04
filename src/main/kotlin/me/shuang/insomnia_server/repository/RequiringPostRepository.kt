package me.shuang.insomnia_server.repository

import me.shuang.insomnia_server.bean.RequiringPost
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

/**
 * @author shuang
 * @date 2021/6/4
 */
interface RequiringPostRepository : JpaRepository<RequiringPost, Int> {

    fun getRequiringPostsByCandidateIdOrderByCreateTimeDesc(candidateId: Int): MutableList<RequiringPost>

    fun getRequiringPostsByRefPost_HumanResourceIdOrderByCreateTimeDesc(refPost_humanResourceId: Int): MutableList<RequiringPost>

    fun getRequiringPostsByPostIdOrderByCreateTimeDesc(postId: Int): MutableList<RequiringPost>

    fun existsByCandidateIdAndPostId(candidateId: Int, postId: Int): Boolean

    @Transactional
    @Modifying
    @Query(
        value = "insert into requiring_post(candidate_id, post_id) values(:candidateId, :postId)",
        nativeQuery = true
    )
    fun insertOne(candidateId: Int, postId: Int): Int
}
