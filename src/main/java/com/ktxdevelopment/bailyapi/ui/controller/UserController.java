package com.ktxdevelopment.bailyapi.ui.controller;

import com.google.common.reflect.TypeToken;
import com.ktxdevelopment.bailyapi.exceptions.OrderServiceException;
import com.ktxdevelopment.bailyapi.exceptions.UserServiceException;
import com.ktxdevelopment.bailyapi.services.OrderService;
import com.ktxdevelopment.bailyapi.services.UserService;
import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import com.ktxdevelopment.bailyapi.shared.user.UserDto;
import com.ktxdevelopment.bailyapi.ui.request.UserDetailsRequestModel;
import com.ktxdevelopment.bailyapi.ui.response.models.ErrorMessages;
import com.ktxdevelopment.bailyapi.ui.response.models.OperationStatusModel;
import com.ktxdevelopment.bailyapi.ui.response.models.RequestOperationStatus;
import com.ktxdevelopment.bailyapi.ui.response.order.OrderRest;
import com.ktxdevelopment.bailyapi.ui.response.user.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public UserRest getUser(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto dto = userService.getUserByUserId(id);
        return modelMapper.map(dto, UserRest.class);
    }



    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

        if (userDetails.getUsername().isBlank() || userDetails.getEmail().isBlank() || userDetails.getPassword().isBlank())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping(
            path = "/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws RuntimeException{
        UserRest userRest = new UserRest();
        ModelMapper modelMapper = new ModelMapper();

        if (userDetails.getUsername().isBlank() || userDetails.getEmail().isBlank() || userDetails.getPassword().isBlank())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto updatedUser = userService.updateUser(id, userDto);
        return modelMapper.map(updatedUser, UserRest.class);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel status = new OperationStatusModel();
        status.setOperationName("Delete");
        userService.deleteUserByUserId(id);
        status.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return status;
    }



    @GetMapping(
            path = "/{id}/orders",
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public List<OrderRest> getUserOrders(@PathVariable String id) {
        List<OrderRest> orderRests = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        List<OrderDto> addressDtos = userService.getOrdersOfUser(id);

        if (addressDtos != null && !addressDtos.isEmpty()) {
            Type listType = new TypeToken<List<OrderRest>>() {}.getType();
            orderRests = modelMapper.map(addressDtos, listType);
        }

        return orderRests;
    }

    @GetMapping(
            path = "/{userId}/orders/{orderId}",
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public OrderRest getUserOrder(@PathVariable String userId , @PathVariable String orderId) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = userService.getUserByUserId(userId);

        if (userDto == null) throw new UserServiceException("User does note exist");

        if (userDto.getOrders().isEmpty()) return null;

        for (OrderDto or: userDto.getOrders()) {
            if (Objects.equals(or.getOrderId(), orderId)) return modelMapper.map(or, OrderRest.class);
        }
        throw new OrderServiceException("Order does not exist");
    }
}