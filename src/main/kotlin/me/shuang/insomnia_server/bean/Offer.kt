package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "offer", schema = "insomnia_database")
open class Offer {
    @Id
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Basic
    @Column(name = "salary", nullable = false)
    var salary: java.math.BigDecimal? = null

    @Basic
    @Column(name = "month_count", nullable = true)
    var monthCount: Int? = null

    @Basic
    @Column(name = "is_passed", nullable = true)
    var isPassed: Byte? = null

    @Basic
    @Column(name = "post_id", nullable = false, insertable = false, updatable = false)
    var postId: Int? = null

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
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    var refPost: Post? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    var refCandidate: Candidate? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "salary = $salary " +
                "monthCount = $monthCount " +
                "isPassed = $isPassed " +
                "postId = $postId " +
                "candidateId = $candidateId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Offer

        if (id != other.id) return false
        if (salary != other.salary) return false
        if (monthCount != other.monthCount) return false
        if (isPassed != other.isPassed) return false
        if (postId != other.postId) return false
        if (candidateId != other.candidateId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

