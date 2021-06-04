package me.shuang.insomnia_server.repository

import me.shuang.insomnia_server.bean.Candidate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CandidateRepository : JpaRepository<Candidate, Int> {

    @Query(
        "select candidate from Candidate candidate " +
                "where (candidate.username = :usernameOrEmail or candidate.email = :usernameOrEmail) " +
                "and candidate.password = :password"
    )
    fun checkLogin(usernameOrEmail: String?, password: String?): Candidate?

    fun existsByUsernameOrEmail(username: String?, email: String?): Boolean

}
