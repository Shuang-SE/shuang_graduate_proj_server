package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "company", schema = "insomnia_database")
open class Company {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @Basic
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Basic
    @Column(name = "location", nullable = false)
    var location: String? = null

    @Basic
    @Column(name = "head_count", nullable = false)
    var headCount: String? = null

    @Basic
    @Column(name = "introduction", nullable = false)
    var introduction: String? = null

    @Basic
    @Column(name = "logo_url", nullable = false)
    var logoUrl: String? = null

    @Basic
    @Column(name = "company_type_id", nullable = false, insertable = false, updatable = false)
    var companyTypeId: Int? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_type_id", referencedColumnName = "id")
    var refCompanyType: CompanyType? = null

//    @OneToMany(mappedBy = "refCompany")
//    var refHumanResources: MutableList<HumanResource>? = null


    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "name = $name " +
                "location = $location " +
                "headCount = $headCount " +
                "introduction = $introduction " +
                "logoUrl = $logoUrl " +
                "companyTypeId = $companyTypeId " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Company

        if (id != other.id) return false
        if (name != other.name) return false
        if (location != other.location) return false
        if (headCount != other.headCount) return false
        if (introduction != other.introduction) return false
        if (logoUrl != other.logoUrl) return false
        if (companyTypeId != other.companyTypeId) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

