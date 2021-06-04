package me.shuang.insomnia_server.repository

import me.shuang.insomnia_server.bean.HumanResource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * @author shuang
 * @date 2021/6/3
 */
interface HRRepository : JpaRepository<HumanResource, Int> {

    fun getHumanResourcesByCompanyIdOrderByCreateTimeDesc(companyId: Int): MutableList<HumanResource>

    @Query(
        "select hr from HumanResource hr " +
                "where (hr.username = :usernameOrEmail or hr.email = :usernameOrEmail) " +
                "and hr.password = :password"
    )
    fun checkLogin(usernameOrEmail: String?, password: String?): HumanResource?

    fun existsByUsernameOrEmail(username: String?, email: String?): Boolean

}
