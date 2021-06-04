package me.shuang.insomnia_server.service

import me.shuang.insomnia_server.bean.Candidate
import me.shuang.insomnia_server.repository.CandidateRepository
import me.shuang.insomnia_server.utils.JwtUtils
import me.shuang.insomnia_server.utils.md5Encrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CandidateService {

    @Autowired
    private lateinit var candidateRepository: CandidateRepository

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    fun getOne(id: Int): Candidate = candidateRepository.getOne(id)

    fun getAll(): MutableList<Candidate> = candidateRepository.findAll()

    fun register(candidate: Candidate): String? {
        if (!candidateRepository.existsByUsernameOrEmail(username = candidate.username, email = candidate.email)) {
            candidate.avatarUrl = "default"
            candidate.password = md5Encrypt(candidate.password)
            candidateRepository.save(candidate)
            return login(candidate, true)
        }
        return null
    }

    fun login(candidate: Candidate, afterRegister: Boolean = false): String? {
        candidateRepository.checkLogin(
            candidate.username,
            if (afterRegister) candidate.password else md5Encrypt(candidate.password)
        )?.let {
            return jwtUtils.generateToken(it.id)
        }
        return null
    }
}
