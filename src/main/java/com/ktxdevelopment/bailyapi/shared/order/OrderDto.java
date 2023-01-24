package com.ktxdevelopment.bailyapi.shared.order;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderStateEntity;
import com.ktxdevelopment.bailyapi.io.entity.user.UserEntity;
import com.ktxdevelopment.bailyapi.shared.product.ProductOrderModelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;

    private String orderId;

    private Double totalPrice;

    private UserEntity userDetails;

    private Double totalDiscount;

    private OrderStateEntity state;

    private List<ProductOrderModelDto> products;
}
