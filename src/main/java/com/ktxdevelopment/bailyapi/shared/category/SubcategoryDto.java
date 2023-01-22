package com.ktxdevelopment.bailyapi.shared.category;

import com.ktxdevelopment.bailyapi.io.entity.category.CategoryEntity;
import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubcategoryDto {
    private Long id;

    private String type;

    private String categoryId;

    private String name;

    private CategoryEntity parentCategory;

    private List<ProductEntity> products;
}
