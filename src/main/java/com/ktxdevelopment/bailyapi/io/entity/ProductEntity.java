package com.ktxdevelopment.bailyapi.io.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue
    private String id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private CategoryEntity category;

    @Column(nullable = false)
    private CategoryEntity subCategory;

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

    @Column(nullable = true)
    private String about;
}