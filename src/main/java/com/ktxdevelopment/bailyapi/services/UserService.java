package com.ktxdevelopment.bailyapi.services;

import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import com.ktxdevelopment.bailyapi.shared.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByEmail(String email);
    UserDto getUserByUserId(String id);
    UserDto updateUser(String id, UserDto userDto);
    void deleteUserByUserId(String id);

    List<OrderDto> getOrdersOfUser(String userId);
}