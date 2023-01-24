package com.ktxdevelopment.bailyapi.shared.order;

import com.ktxdevelopment.bailyapi.ui.request.product.ProductRequestModel;

import java.io.Serializable;

public class ProductOrderModelDto implements Serializable {

        private Long id;

        private Integer number;

        private ProductRequestModel product;
}
