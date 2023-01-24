package com.ktxdevelopment.bailyapi.services.impl;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderEntity;
import com.ktxdevelopment.bailyapi.io.repo.OrderRepository;
import com.ktxdevelopment.bailyapi.services.OrderService;
import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import com.ktxdevelopment.bailyapi.util.Mapper;
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

        return mapper().mapList(orders, OrderDto.class);
    }

    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity newOrder = orderRepository.save(mapper().mapComplex(order, OrderEntity.class));
        return mapper().mapComplex(newOrder, OrderDto.class);
    }

    private Mapper mapper() {
        return new Mapper();
    }
}
