package com.ktxdevelopment.bailyapi.security.auth;

import com.ktxdevelopment.bailyapi.io.entity.user.Role;
import com.ktxdevelopment.bailyapi.io.entity.user.UserEntity;
import com.ktxdevelopment.bailyapi.io.repo.UserRepository;
import com.ktxdevelopment.bailyapi.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = UserEntity.builder()
        .userId(UUID.randomUUID().toString())
        .username(request.getUsername())
        .email(request.getEmail())
        .encryptedPassword(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail());
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}