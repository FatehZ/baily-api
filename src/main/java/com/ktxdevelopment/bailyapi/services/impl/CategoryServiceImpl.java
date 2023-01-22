package com.ktxdevelopment.bailyapi.services.impl;

import com.ktxdevelopment.bailyapi.io.entity.category.CategoryEntity;
import com.ktxdevelopment.bailyapi.io.entity.category.SubcategoryEntity;
import com.ktxdevelopment.bailyapi.io.repo.CategoryRepository;
import com.ktxdevelopment.bailyapi.io.repo.SubcategoryRepository;
import com.ktxdevelopment.bailyapi.services.CategoryService;
import com.ktxdevelopment.bailyapi.shared.category.CategoryDto;
import com.ktxdevelopment.bailyapi.shared.category.SubcategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired CategoryRepository categoryRepository;

    @Autowired SubcategoryRepository subcategoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {

        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (CategoryEntity en: categoryEntities) {
            categoryDtos.add(mapper().map(en, CategoryDto.class));
        }

        return categoryDtos;
    }

    @Override
    public List<SubcategoryDto> getAllSubCategories() {

        List<SubcategoryEntity> subcategoryEntities = subcategoryRepository.findAll();

        List<SubcategoryDto> subcategoryDtos = new ArrayList<>();

        for (SubcategoryEntity en: subcategoryEntities) {
            subcategoryDtos.add(mapper().map(en, SubcategoryDto.class));
        }
        return subcategoryDtos;
    }

    @Override
    public CategoryDto getCategoryByCategoryId(String categoryId) {
        CategoryEntity category = categoryRepository.findByCategoryId(categoryId);
        return mapper().map(category, CategoryDto.class);

    }

    @Override
    public SubcategoryDto getSubcategoryById(String categoryId, String subcategoryId) {
        SubcategoryEntity category = subcategoryRepository.findByCategoryId(subcategoryId);
        return mapper().map(category, SubcategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity category = mapper().map(categoryDto, CategoryEntity.class);
        categoryRepository.save(category);
        return categoryDto;
    }

    @Override
    public SubcategoryDto createSubcategory(SubcategoryDto subcategoryDto) {
        SubcategoryEntity category = mapper().map(subcategoryDto, SubcategoryEntity.class);
        subcategoryRepository.save(category);
        return subcategoryDto;
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name);
        return mapper().map(categoryEntity, CategoryDto.class);
    }





    private ModelMapper mapper() { return new ModelMapper(); }
}