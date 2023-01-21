package com.ktxdevelopment.bailyapi.services.impl;

import com.ktxdevelopment.bailyapi.io.entity.ProductEntity;
import com.ktxdevelopment.bailyapi.io.entity.UserEntity;
import com.ktxdevelopment.bailyapi.io.repo.ProductRepository;
import com.ktxdevelopment.bailyapi.services.ProductService;
import com.ktxdevelopment.bailyapi.shared.ProductDto;
import com.ktxdevelopment.bailyapi.shared.UserDto;
import com.ktxdevelopment.bailyapi.ui.response.ProductRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepo;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        List<ProductEntity> productEntities = productRepo.findAll();

        for (ProductEntity pr : productEntities) {
            products.add(modelMapper.map(pr, ProductDto.class));
        }
        return products;
    }

    @Override
    public List<ProductDto> getPaginatedProducts(int page, int limit) {
        List<ProductDto> products = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, limit);
        List<ProductEntity> productEntities = productRepo.findAll(pageable).getContent();

        for (ProductEntity pr : productEntities) {
            products.add(modelMapper.map(pr, ProductDto.class));
        }
        return products;
    }
}
