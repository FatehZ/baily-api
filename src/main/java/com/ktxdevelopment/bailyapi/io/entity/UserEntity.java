package com.ktxdevelopment.bailyapi.io.entity;

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
@Entity(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false, length = 25)
    private String mobile;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = false, length = 100)
    private String address;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    List<OrderEntity> orders;
}
