package com.ktxdevelopment.bailyapi.services.impl;

import com.ktxdevelopment.bailyapi.io.entity.UserEntity;
import com.ktxdevelopment.bailyapi.io.repo.UserRepository;
import com.ktxdevelopment.bailyapi.services.UserService;
import com.ktxdevelopment.bailyapi.shared.UserDto;
import com.ktxdevelopment.bailyapi.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

        for (int i=0; i <userDto.getAddresses().size(); i++) {
            AddressDto address = userDto.getAddresses().get(i);
            address.setUserDetails(userDto);
            address.setAddressId(utils.generateAddressId(20));
            userDto.getAddresses().set(i, address);
        }

        UserEntity entity = modelMapper.map(userDto, UserEntity.class);
        entity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        entity.setUserId(utils.generateUserId(20));

        UserEntity storedUser = userRepo.save(entity);

        return modelMapper.map(storedUser ,UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepo.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserEntity entity = userRepo.findByUserId(id);
        if (entity == null) throw new UsernameNotFoundException(id);

        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public void deleteUserByUserId(String id) {
        UserEntity entity = userRepo.findByUserId(id);
        if (entity == null) throw new UsernameNotFoundException(id);
        userRepo.delete(entity);
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {

        UserEntity currentEntity = userRepo.findByUserId(id);

        if (currentEntity == null) throw new RuntimeException("Record already exists");

        currentEntity.setLastName(userDto.getLastName());
        currentEntity.setFirstName(userDto.getFirstName());

        UserEntity newEntity = userRepo.save(currentEntity);


        return modelMapper.map(newEntity ,UserDto.class);

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity entity = userRepo.findByEmail(email);
        if (entity == null) throw new UsernameNotFoundException(email);
        return new User(entity.getEmail(), entity.getEncryptedPassword(), new ArrayList<>());
    }
}
