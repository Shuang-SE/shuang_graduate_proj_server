package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "sent_resume", schema = "insomnia_database")
open class SentResume {
    @Id
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Basic
    @Column(name = "resume_id", nullable = false, insertable = false, updatable = false)
    var resumeId: Int? = null

    @Basic
    @Column(name = "human_resource_id", nullable = false, insertable = false, updatable = false)
    var humanResourceId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    var refResume: Resume? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id")
    var refHumanResource: HumanResource? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "resumeId = $resumeId " +
                "humanResourceId = $humanResourceId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SentResume

        if (id != other.id) return false
        if (resumeId != other.resumeId) return false
        if (humanResourceId != other.humanResourceId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

