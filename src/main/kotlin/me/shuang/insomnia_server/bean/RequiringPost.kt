package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "requiring_post", schema = "insomnia_database")
open class RequiringPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Basic
    @Column(name = "candidate_id", nullable = false, insertable = false, updatable = false)
    var candidateId: Int? = null

    @Basic
    @Column(name = "post_id", nullable = false, insertable = false, updatable = false)
    var postId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    var refCandidate: Candidate? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    var refPost: Post? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "candidateId = $candidateId " +
                "postId = $postId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RequiringPost

        if (id != other.id) return false
        if (candidateId != other.candidateId) return false
        if (postId != other.postId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

