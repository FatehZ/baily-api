package com.ktxdevelopment.bailyapi.services.impl;

import com.ktxdevelopment.bailyapi.exceptions.UserServiceException;
import com.ktxdevelopment.bailyapi.io.entity.user.UserEntity;
import com.ktxdevelopment.bailyapi.io.repo.UserRepository;
import com.ktxdevelopment.bailyapi.services.UserService;
import com.ktxdevelopment.bailyapi.shared.Utils;
import com.ktxdevelopment.bailyapi.shared.order.OrderDto;
import com.ktxdevelopment.bailyapi.shared.user.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired UserRepository userRepo;
    @Autowired Utils utils;
    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity storedUserEntity = userRepo.findByEmail(userDto.getEmail());

        if (storedUserEntity != null) throw new RuntimeException("Record already exists");

        UserEntity entity = mapper().map(userDto, UserEntity.class);
        entity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        entity.setUserId(utils.generateUserId(20));

        UserEntity storedUser = userRepo.save(entity);

        return mapper().map(storedUser ,UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepo.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return mapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserEntity entity = userRepo.findByUserId(id);
        if (entity == null) throw new UsernameNotFoundException(id);

        return mapper().map(entity, UserDto.class);
    }

    @Override
    public void deleteUserByUserId(String id) {
        UserEntity entity = userRepo.findByUserId(id);
        if (entity == null) throw new UsernameNotFoundException(id);
        userRepo.delete(entity);
    }

    @Override
    public List<OrderDto> getOrdersOfUser(String userId) {
        UserEntity user = userRepo.findByUserId(userId);

        if (user == null) throw new UserServiceException("User not found");

        Type type = new TypeToken<List<OrderDto>>() {}.getType();

        return mapper().map(user.getOrders(), type);
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {

        UserEntity currentEntity = userRepo.findByUserId(id);
        if (currentEntity == null) throw new RuntimeException("Record already exists");
        currentEntity.setUsername(userDto.getUsername());
        UserEntity newEntity = userRepo.save(currentEntity);

        return mapper().map(newEntity ,UserDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity entity = userRepo.findByEmail(email);
        if (entity == null) throw new UsernameNotFoundException(email);
        return new User(entity.getEmail(), entity.getEncryptedPassword(), new ArrayList<>());
    }

    private ModelMapper mapper() {
        return new ModelMapper();
    }
}
