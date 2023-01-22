package com.ktxdevelopment.bailyapi.ui.request.order;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderStateEntity;
import com.ktxdevelopment.bailyapi.shared.product.ProductOrderModelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderRequestModel {
    private Double totalPrice;
    private Double totalDiscount;
    private OrderStateEntity state;
    private List<ProductOrderModelDto> products;
}