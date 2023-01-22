package com.ktxdevelopment.bailyapi.services.impl;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderEntity;
import com.ktxdevelopment.bailyapi.io.repo.OrderRepository;
import com.ktxdevelopment.bailyapi.services.OrderService;
import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto getOrder(String orderId) {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        Type type = new TypeToken<OrderDto>() {}.getType();
        return mapper().map(order, type);
    }

    @Override
    public List<OrderDto> getAllOrders(String id) {
        List<OrderEntity> orders = orderRepository.findAll();
        Type type = new TypeToken<List<OrderDto>>() {}.getType();

        return mapper().map(orders, type);
    }

    private ModelMapper mapper() {
        return new ModelMapper();
    }
}
