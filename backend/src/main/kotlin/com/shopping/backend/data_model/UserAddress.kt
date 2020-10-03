package com.shopping.backend.data_model

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener::class)
@DynamicInsert
@DynamicUpdate
@Table(name = "user_addresses")
@XmlRootElement
class UserAddress : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adress_id")
    var adressId: Long? = null

    @Basic(optional = false)
    @Column(name = "user_id")
    var userId: String? = null

    @Basic(optional = false)
    @Column(name = "adress")
    var adress: String? = null

    @Basic(optional = false)
    @Column(name = "phone")
    var phone: String? = null

    @Basic(optional = false)
    @Column(name = "fax")
    var fax: String? = null

    @Basic(optional = false)
    @Column(name = "city")
    var city: String? = null

    @Basic(optional = false)
    @Column(name = "country")
    var country: String? = null

    @Basic(optional = false)
    @Column(name = "status")
    var status = 0

    companion object {
        const val serialVersionUID = 1L
    }
}