package com.ktxdevelopment.bailyapi.io.entity.order;

import com.ktxdevelopment.bailyapi.io.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class OrderEntity implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;

    private List<ProductOrderModelEntity> products;
}
