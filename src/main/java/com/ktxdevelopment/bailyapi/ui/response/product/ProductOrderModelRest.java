package com.ktxdevelopment.bailyapi.ui.response.product;


import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductOrderModelRest {

    private Integer number = 0;

    private ProductEntity product;
}
