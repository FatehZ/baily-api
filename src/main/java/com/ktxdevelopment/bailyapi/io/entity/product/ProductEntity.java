package com.ktxdevelopment.bailyapi.io.entity.product;


import com.ktxdevelopment.bailyapi.io.entity.category.CategoryEntity;
import com.ktxdevelopment.bailyapi.io.entity.order.ProductOrderModelEntity;
import com.ktxdevelopment.bailyapi.io.entity.category.SubcategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @Column(nullable = false)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "subcategories_id")
    private SubcategoryEntity subcategory;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private List<String> sizeList;

    @Column(nullable = false)
    private List<String> imageList;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double discount = 0.0;

    @Column(nullable = false)
    private Boolean available = true;

    @Column
    private String about;

    @ManyToMany
    @JoinColumn(name = "orderProducts_id")
    private ProductOrderModelEntity orderProductDetails;
}