package com.ktxdevelopment.bailyapi.ui.response;

import com.ktxdevelopment.bailyapi.io.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRest {

    private String userId;

    private String username;

    private String email;

    private String mobile;

    private String address;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    List<OrderEntity> orders;
}
