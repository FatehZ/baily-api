package com.ktxdevelopment.bailyapi.io.entity.order;

import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "orderProducts")
public class ProductOrderModelEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer number = 0;

    @ManyToMany(mappedBy = "orderProductDetails", cascade = CascadeType.ALL)
    private ProductEntity product;
}