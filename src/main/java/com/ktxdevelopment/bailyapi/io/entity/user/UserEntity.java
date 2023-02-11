package com.ktxdevelopment.bailyapi.io.entity.user;

import com.ktxdevelopment.bailyapi.io.entity.order.OrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity(name = "users")
@NoArgsConstructor
public class UserEntity implements Serializable, UserDetails {

    @Id @GeneratedValue private Long id;

    @Column(nullable = false) private String userId;

    @Column(nullable = false, length = 50) private String username;

    @Column(nullable = false, length = 80) private String email;

    @Column(nullable = true, length = 25) private String mobile;

    @Column(nullable = false) private String encryptedPassword;

    @Column(nullable = true, length = 100) private String address = "";
    @Enumerated(EnumType.STRING) private Role role = Role.USER;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL) List<OrderEntity> orders = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(new SimpleGrantedAuthority(role.name())); }

    @Override
    public String getPassword() {return encryptedPassword;}

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}
