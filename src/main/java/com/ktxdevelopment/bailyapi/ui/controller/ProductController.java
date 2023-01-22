package com.ktxdevelopment.bailyapi.ui.controller;

import com.ktxdevelopment.bailyapi.services.ProductService;
import com.ktxdevelopment.bailyapi.shared.product.ProductDto;
import com.ktxdevelopment.bailyapi.ui.response.product.ProductRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public List<ProductRest> getPaginatedProducts(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit){
        List<ProductRest> products = new ArrayList<>();

        List<ProductDto> productDtoList = productService.getPaginatedProducts(page, limit);

        ModelMapper modelMapper = new ModelMapper();

        for (ProductDto dto : productDtoList) { products.add(modelMapper.map(dto, ProductRest.class)); }

        return products;
    }


    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public List<ProductRest> getAllProducts(){
        List<ProductRest> products = new ArrayList<>();
        List<ProductDto> productDtoList = productService.getAllProducts();
        ModelMapper modelMapper = new ModelMapper();

        for (ProductDto dto : productDtoList) { products.add(modelMapper.map(dto, ProductRest.class)); }
        return products;
    }

}
