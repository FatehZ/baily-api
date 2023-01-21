package com.ktxdevelopment.bailyapi.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private Double totalDiscount;

    @Column(nullable = false)
    private OrderStateEntity state;
}
