package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "resume", schema = "insomnia_database")
open class Resume {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @Basic
    @Column(name = "birth_time", nullable = false)
    var birthTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "introduction", nullable = false)
    var introduction: String? = null

    @Basic
    @Column(name = "honor", nullable = false)
    var honor: String? = null

    @Basic
    @Column(name = "prize", nullable = false)
    var prize: String? = null

    @Basic
    @Column(name = "experience", nullable = false)
    var experience: String? = null

    @Basic
    @Column(name = "is_file", nullable = false)
    var isFile: Byte? = null

    @Basic
    @Column(name = "file_content_url", nullable = true)
    var fileContentUrl: String? = null

    @Basic
    @Column(name = "candidate_id", nullable = false, insertable = false, updatable = false)
    var candidateId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    var refCandidate: Candidate? = null

//    @OneToMany(mappedBy = "refResume")
//    var refSentResumes: MutableList<SentResume>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "birthTime = $birthTime " +
                "introduction = $introduction " +
                "honor = $honor " +
                "prize = $prize " +
                "experience = $experience " +
                "isFile = $isFile " +
                "fileContentUrl = $fileContentUrl " +
                "candidateId = $candidateId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Resume

        if (id != other.id) return false
        if (birthTime != other.birthTime) return false
        if (introduction != other.introduction) return false
        if (honor != other.honor) return false
        if (prize != other.prize) return false
        if (experience != other.experience) return false
        if (isFile != other.isFile) return false
        if (fileContentUrl != other.fileContentUrl) return false
        if (candidateId != other.candidateId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

