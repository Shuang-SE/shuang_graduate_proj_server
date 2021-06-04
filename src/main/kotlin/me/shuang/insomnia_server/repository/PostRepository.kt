package me.shuang.insomnia_server.repository

import me.shuang.insomnia_server.bean.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int> {

    fun getPostsByOrderByCreateTimeDesc(pageable: Pageable): Page<Post>

    fun getPostsByPostTypeIdOrderByCreateTimeDesc(postTypeId: Int, pageable: Pageable): Page<Post>

    fun getPostsByHumanResourceIdOrderByCreateTimeDesc(humanResourceId: Int): MutableList<Post>
}
