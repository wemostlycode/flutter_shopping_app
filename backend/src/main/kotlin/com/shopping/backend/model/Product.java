package com.shopping.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity(name = "product")
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"handler", "hibernate_lazy_initializer"})
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id", unique = true)
    private String productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "quantity_in_stock")
    private String quantityInStock;
    @Column(name = "price")
    private String price;
    @Column(name = "discount")
    private Double discount;


}
