package com.ktxdevelopment.bailyapi.services;

import com.ktxdevelopment.bailyapi.shared.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    List<ProductDto> getAllProducts();

    List<ProductDto> getPaginatedProducts(int page, int limit);
}
