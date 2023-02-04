package com.ktxdevelopment.bailyapi.shared.user;

import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String encryptedPassword;
    private List<OrderDto> orders = new ArrayList<>();
}
