package com.shopping.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "products")
@XmlRootElement
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Long productId;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "browsing_name")
    private String browsingName;

    @Basic(optional = false)
    @Column(name = "sale_price")
    private double salePrice;

    @Basic(optional = false)
    @Column(name = "list_price")
    private double listPrice;

    @Basic(optional = false)
    @Column(name = "default_image")
    private String defaultImage;

    @Basic(optional = false)
    @Column(name = "overview")
    private String overview;

    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;

    @Basic(optional = false)
    @Column(name = "status")
    private int status;

    @Column(name = "description")
    private String description;

    @Basic(optional = false)
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

}
