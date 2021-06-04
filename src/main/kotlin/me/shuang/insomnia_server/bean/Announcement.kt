package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "announcement", schema = "insomnia_database")
open class Announcement {
    @Id
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Basic
    @Column(name = "title", nullable = false)
    var title: String? = null

    @Basic
    @Column(name = "content", nullable = false)
    var content: String? = null

    @Basic
    @Column(name = "release_time", nullable = true, insertable = false)
    var releaseTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "admin_id", nullable = false, insertable = false, updatable = false)
    var adminId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    var refAdmin: Admin? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "title = $title " +
                "content = $content " +
                "releaseTime = $releaseTime " +
                "adminId = $adminId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Announcement

        if (id != other.id) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (releaseTime != other.releaseTime) return false
        if (adminId != other.adminId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

