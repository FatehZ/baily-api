package com.ktxdevelopment.bailyapi.shared.category;

import com.ktxdevelopment.bailyapi.io.entity.category.SubcategoryEntity;
import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    private String categoryId;

    private String name;

    private List<SubcategoryEntity> subCategories;

    private List<ProductEntity> products;
}