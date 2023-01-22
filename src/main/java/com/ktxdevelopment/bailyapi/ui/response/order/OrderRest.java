package com.ktxdevelopment.bailyapi.ui.response.order;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderStateEntity;
import com.ktxdevelopment.bailyapi.shared.product.ProductOrderModelDto;

import java.util.List;

public class OrderRest {
    private String orderId;

    private Double totalPrice;

    private Double totalDiscount;

    private OrderStateEntity state;

    private List<ProductOrderModelDto> products;
}
