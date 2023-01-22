package com.ktxdevelopment.bailyapi.ui.controller;


import com.ktxdevelopment.bailyapi.exceptions.CategoryServiceException;
import com.ktxdevelopment.bailyapi.io.entity.category.SubcategoryEntity;
import com.ktxdevelopment.bailyapi.services.CategoryService;
import com.ktxdevelopment.bailyapi.shared.category.CategoryDto;
import com.ktxdevelopment.bailyapi.shared.category.SubcategoryDto;
import com.ktxdevelopment.bailyapi.ui.request.category.CategoryRequestModel;
import com.ktxdevelopment.bailyapi.ui.request.category.SubcategoryRequestModel;
import com.ktxdevelopment.bailyapi.ui.response.category.CategoryRest;
import com.ktxdevelopment.bailyapi.ui.response.category.SubcategoryRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("categories")
@RestController
public class CategoryController {

    @Autowired CategoryService categoryService;

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public CategoryRest getCategory(@PathVariable String id) {
        CategoryDto dto = categoryService.getCategoryByCategoryId(id);
        return mapper().map(dto, CategoryRest.class);
    }


    @GetMapping(path = "/{categoryId}/{subCategoryId}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public SubcategoryRest getSubcategory(@PathVariable String categoryId, @PathVariable String subcategoryId) {
        SubcategoryDto dto = categoryService.getSubcategoryById(categoryId, subcategoryId);
        return mapper().map(dto, SubcategoryRest.class);
    }



    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public CategoryRest createCategory(@RequestBody CategoryRequestModel categoryRequestModel) {

        CategoryDto categoryDto = categoryService.getCategoryByName(categoryRequestModel.getName());

        if (categoryDto != null) throw new CategoryServiceException("Category already exists");

        CategoryDto createdCategory = categoryService.createCategory(mapper().map(categoryRequestModel, CategoryDto.class));

        return mapper().map(createdCategory, CategoryRest.class);
    }


    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public CategoryRest createSubcategory(@RequestBody SubcategoryRequestModel subcategoryRequestModel) {

        SubcategoryDto subcategoryDto = mapper().map(subcategoryRequestModel, SubcategoryDto.class);

        CategoryDto parentCategory = categoryService.getCategoryByCategoryId(subcategoryDto.getParentCategory().getCategoryId());

        // The same subcategory name can exist in different categories.
        if (parentCategory != null) {
            for(SubcategoryEntity sub : parentCategory.getSubCategories()) {
                if (Objects.equals(sub.getName(), subcategoryRequestModel.getName())) throw new CategoryServiceException("Subcategory already exists");
            }
        }

        SubcategoryDto createdCategory = categoryService.createSubcategory(subcategoryDto);

        return mapper().map(createdCategory, CategoryRest.class);   //todo modelmapper convert to typetoken
    }

    private ModelMapper mapper() {
        return new ModelMapper();
    }
}
