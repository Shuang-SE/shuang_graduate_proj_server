package me.shuang.insomnia_server.bean

import javax.persistence.*

@Entity
@Table(name = "company_type", schema = "insomnia_database")
open class CompanyType {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @Basic
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Basic
    @Column(name = "create_time", nullable = true, insertable = false)
    var createTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "update_time", nullable = true, insertable = false)
    var updateTime: java.sql.Timestamp? = null

//    @OneToMany(mappedBy = "refCompanyType")
//    var refCompanies: MutableList<Company>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "name = $name " +
                "createTime = $createTime " +
                "updateTime = $updateTime " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CompanyType

        if (id != other.id) return false
        if (name != other.name) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

