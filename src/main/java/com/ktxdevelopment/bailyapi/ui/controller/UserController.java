package com.ktxdevelopment.bailyapi.ui.controller;

import com.google.common.reflect.TypeToken;
import com.ktxdevelopment.bailyapi.exceptions.UserServiceException;
import com.ktxdevelopment.bailyapi.services.UserService;
import com.ktxdevelopment.bailyapi.shared.UserDto;
import com.ktxdevelopment.bailyapi.ui.request.UserDetailsRequestModel;
import com.ktxdevelopment.bailyapi.ui.response.*;
import com.ktxdevelopment.bailyapi.ui.response.models.ErrorMessages;
import com.ktxdevelopment.bailyapi.ui.response.models.OperationStatusModel;
import com.ktxdevelopment.bailyapi.ui.response.models.RequestOperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public UserRest getUser(@PathVariable String id) {
        UserDto dto = userService.getUserByUserId(id);
        return modelMapper.map(dto, UserRest.class);
    }



    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

        if (userDetails.getFirstName().isBlank() || userDetails.getLastName().isBlank() || userDetails.getEmail().isBlank() || userDetails.getPassword().isBlank())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping(
            path = "/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails){
        UserRest userRest = new UserRest();

        if (userDetails.getUsername() || userDetails.getEmail().isBlank() || userDetails.getPassword().isBlank())
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
        status.setOperationName(RequestOperationName.DELETE.name());
        userService.deleteUserByUserId(id);
        status.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return status;
    }



    @GetMapping(
            path = "/{id}/orders",
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public Resources<AddressRest> getUserAddresses(@PathVariable String id) {
        List<AddressRest> addressRests = new ArrayList<>();

        List<AddressDto> addressDtos = orderService.getAddresses(id);

        if (addressDtos != null && !addressDtos.isEmpty()) {
            Type listType = new TypeToken<List<AddressRest>>() {}.getType();
            addressRests = modelMapper.map(addressDtos, listType);
        }

        return addressRests;
    }

    @GetMapping(
            path = "/{userId}/orders/{orderId}",
            produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE }
    )
    public AddressRest getUserOrder(@PathVariable String userId , @PathVariable String orderId) {
        OrderDto orderDto = orderService.getAddress(orderId);

        AddressRest addressRest = modelMapper.map(addressDto, AddressRest.class);
        return addressRest;
    }
}