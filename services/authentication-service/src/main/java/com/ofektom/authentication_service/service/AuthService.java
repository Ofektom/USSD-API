package com.ofektom.authentication_service.service;

import com.ofektom.authentication_service.dto.ApiResponse;
import com.ofektom.authentication_service.dto.AuthResponse;
import com.ofektom.authentication_service.dto.LoginDto;
import com.ofektom.authentication_service.dto.SignupDto;

public interface AuthService {
    ApiResponse<AuthResponse> saveUser(SignupDto signupDto);
    ApiResponse<AuthResponse> login(LoginDto loginDto);
    void validate(String token);
}
