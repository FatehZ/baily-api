package com.ktxdevelopment.bailyapi.services;

import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import com.ktxdevelopment.bailyapi.ui.request.order.OrderRequestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto getOrder(String orderId);

    List<OrderDto> getAllOrders(String id);

    OrderDto createOrder(OrderRequestModel order);
}
