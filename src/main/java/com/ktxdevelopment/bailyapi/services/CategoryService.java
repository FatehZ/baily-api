package com.ktxdevelopment.bailyapi.services;

import com.ktxdevelopment.bailyapi.shared.category.CategoryDto;
import com.ktxdevelopment.bailyapi.shared.category.SubcategoryDto;

import java.util.List;

public interface CategoryService{
    List<CategoryDto> getAllCategories();

    List<SubcategoryDto> getAllSubCategories();

    CategoryDto getCategoryByCategoryId(String categoryId);

    SubcategoryDto getSubcategoryById(String categoryId, String subcategoryId);

    CategoryDto createCategory(CategoryDto categoryDto);

    SubcategoryDto createSubcategory(SubcategoryDto subcategoryDto);

    CategoryDto getCategoryByName(String name);
}