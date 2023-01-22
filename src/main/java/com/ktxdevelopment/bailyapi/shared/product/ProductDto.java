package com.ktxdevelopment.bailyapi.shared.product;

import com.ktxdevelopment.bailyapi.io.entity.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;

    private String productId;

    private CategoryEntity category;

    private CategoryEntity subCategory;

    private String name;

    private List<String> sizeList;

    private List<String> imageList;

    private Double price;

    private Double discount = 0.0;

    private Boolean available = true;

    private String about;
}
