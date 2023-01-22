package com.ktxdevelopment.bailyapi.io.entity.order;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_states")
public class OrderStateEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String finished;

    @Column(nullable = true)
    private String ordered;

    @Column(nullable = true)
    private String cancelled;
}
