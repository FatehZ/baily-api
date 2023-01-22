package com.ktxdevelopment.bailyapi.shared.product;

import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderModelDto {

    private Long id;

    private Integer number = 0;

    private ProductEntity product;
}
