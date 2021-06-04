package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "post", schema = "insomnia_database")
open class Post {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @Basic
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Basic
    @Column(name = "salary_range", nullable = false)
    var salaryRange: String? = null

    @Basic
    @Column(name = "working_range", nullable = false)
    var workingRange: String? = null

    @Basic
    @Column(name = "resting", nullable = false)
    var resting: String? = null

    @Basic
    @Column(name = "image_url", nullable = false)
    var imageUrl: String? = null

    @Basic
    @Column(name = "desc", nullable = false)
    var desc: String? = null

    @Basic
    @Column(name = "post_type_id", nullable = false, insertable = false, updatable = false)
    var postTypeId: Int? = null

    @Basic
    @Column(name = "human_resource_id", nullable = false, insertable = false, updatable = false)
    var humanResourceId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

//    @OneToMany(mappedBy = "refPost")
//    var refOffers: MutableList<Offer>? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_type_id", referencedColumnName = "id")
    var refPostType: PostType? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id")
    var refHumanResource: HumanResource? = null

//    @OneToMany(mappedBy = "refPost")
//    var refRequiringPosts: MutableList<RequiringPost>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "name = $name " +
                "salaryRange = $salaryRange " +
                "workingRange = $workingRange " +
                "resting = $resting " +
                "imageUrl = $imageUrl " +
                "desc = $desc " +
                "postTypeId = $postTypeId " +
                "humanResourceId = $humanResourceId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Post

        if (id != other.id) return false
        if (name != other.name) return false
        if (salaryRange != other.salaryRange) return false
        if (workingRange != other.workingRange) return false
        if (resting != other.resting) return false
        if (imageUrl != other.imageUrl) return false
        if (desc != other.desc) return false
        if (postTypeId != other.postTypeId) return false
        if (humanResourceId != other.humanResourceId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

