package me.shuang.insomnia_server.repository

import me.shuang.insomnia_server.bean.PostType
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author shuang
 * @date 2021/6/3
 */
interface PostTypeRepository: JpaRepository<PostType, Int> {
}
