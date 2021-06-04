package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "candidate", schema = "insomnia_database")
open class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Basic
    @Column(name = "username", nullable = false)
    var username: String? = null

    @Basic
    @Column(name = "password", nullable = false)
    var password: String? = null

    @Basic
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Basic
    @Column(name = "email", nullable = false)
    var email: String? = null

    @Basic
    @Column(name = "avatar_url", nullable = false)
    var avatarUrl: String? = null

    @Basic
    @Column(name = "type", nullable = false)
    var type: String? = null

    @Basic
    @Column(name = "register_time", nullable = true, insertable = false)
    var registerTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

//    @OneToMany(mappedBy = "refCandidate")
//    var refOffers: MutableList<Offer>? = null

//    @OneToMany(mappedBy = "refCandidate")
//    var refRequiringPosts: MutableList<RequiringPost>? = null

//    @OneToMany(mappedBy = "refCandidate")
//    var refResumes: MutableList<Resume>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "username = $username " +
                "password = $password " +
                "name = $name " +
                "email = $email " +
                "avatarUrl = $avatarUrl " +
                "type = $type " +
                "registerTime = $registerTime " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Candidate

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (avatarUrl != other.avatarUrl) return false
        if (type != other.type) return false
        if (registerTime != other.registerTime) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

