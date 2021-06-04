package me.shuang.insomnia_server.service

import me.shuang.insomnia_server.bean.Post
import me.shuang.insomnia_server.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

/**
 * @author shuang
 * @date 2021/6/2
 */
@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    val sort = Sort.by(Sort.Direction.DESC, "createTime")

    fun getPostList(page: Int, pageSize: Int): Page<Post> =
        postRepository.getPostsByOrderByCreateTimeDesc(PageRequest.of(page, pageSize, sort))

    fun getPostListByPostTypeId(id: Int, page: Int, pageSize: Int) =
        postRepository.getPostsByPostTypeIdOrderByCreateTimeDesc(id, PageRequest.of(page, pageSize, sort))

}
