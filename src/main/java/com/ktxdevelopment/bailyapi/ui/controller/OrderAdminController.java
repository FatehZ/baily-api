package com.ktxdevelopment.bailyapi.ui.controller;


import com.ktxdevelopment.bailyapi.exceptions.OrderServiceException;
import com.ktxdevelopment.bailyapi.services.OrderService;
import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import com.ktxdevelopment.bailyapi.ui.response.order.OrderRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderAdminController{

    @Autowired OrderService orderService;


    @RequestMapping("/{id}")
    OrderRest getOrderById(@PathVariable String id) {
        OrderDto orderDto = orderService.getOrder(id);
        if (orderDto == null) throw new OrderServiceException("Order does not exist");

        Type type = new TypeToken<List<OrderRest>>() {}.getType();

        return mapper().map(type, type);
    }

    ModelMapper mapper() {
        return new ModelMapper();
    }
}
