package com.ktxdevelopment.bailyapi.io.repo;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(String orderId);
}
