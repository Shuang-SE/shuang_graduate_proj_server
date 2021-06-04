package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "human_resource", schema = "insomnia_database")
open class HumanResource {
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
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Basic
    @Column(name = "email", nullable = false)
    var email: String? = null

    @Basic
    @Column(name = "post_name", nullable = false)
    var postName: String? = null

    @Basic
    @Column(name = "avatar_url", nullable = false)
    var avatarUrl: String? = null

    @Basic
    @Column(name = "introduction", nullable = false)
    var introduction: String? = null

    @Basic
    @Column(name = "is_at_post", nullable = true)
    var isAtPost: Byte? = null

    @Basic
    @Column(name = "company_id", nullable = false, insertable = false, updatable = false)
    var companyId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    var refCompany: Company? = null

//    @OneToMany(mappedBy = "refHumanResource")
//    var refPosts: MutableList<Post>? = null

//    @OneToMany(mappedBy = "refHumanResource")
//    var refSentResumes: MutableList<SentResume>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "username = $username " +
                "password = $password " +
                "name = $name " +
                "email = $email " +
                "postName = $postName " +
                "avatarUrl = $avatarUrl " +
                "introduction = $introduction " +
                "isAtPost = $isAtPost " +
                "companyId = $companyId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as HumanResource

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (postName != other.postName) return false
        if (avatarUrl != other.avatarUrl) return false
        if (introduction != other.introduction) return false
        if (isAtPost != other.isAtPost) return false
        if (companyId != other.companyId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

