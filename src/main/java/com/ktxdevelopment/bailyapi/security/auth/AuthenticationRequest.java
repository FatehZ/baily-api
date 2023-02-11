package com.ktxdevelopment.bailyapi.security.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
  private String email;
  String password;
}