package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "admin", schema = "insomnia_database")
open class Admin {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @Basic
    @Column(name = "username", nullable = false)
    var username: String? = null

    @Basic
    @Column(name = "password", nullable = false)
    var password: String? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

//    @OneToMany(mappedBy = "refAdmin")
//    var refAnnouncements: MutableList<Announcement>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "username = $username " +
                "password = $password " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Admin

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

