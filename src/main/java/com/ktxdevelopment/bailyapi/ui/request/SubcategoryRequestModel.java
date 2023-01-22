package com.ktxdevelopment.bailyapi.ui.request;


import com.ktxdevelopment.bailyapi.ui.response.category.CategoryRest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubcategoryRequestModel {
    private String name;
    private CategoryRest parentCategory;
}