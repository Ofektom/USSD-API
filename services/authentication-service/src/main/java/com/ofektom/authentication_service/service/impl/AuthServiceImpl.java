package com.ofektom.authentication_service.service.impl;


import com.ofektom.authentication_service.dto.ApiResponse;
import com.ofektom.authentication_service.dto.AuthResponse;
import com.ofektom.authentication_service.repository.AuthRepository;
import com.ofektom.authentication_service.dto.LoginDto;
import com.ofektom.authentication_service.dto.SignupDto;
import com.ofektom.authentication_service.entity.User;
import com.ofektom.authentication_service.service.AuthService;
import com.ofektom.authentication_service.utils.UserMapper;
import com.ofektom.authentication_service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService, AuthService {
    private final AuthRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils utils;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not Found"));
    }

    @Override
    public ApiResponse<AuthResponse> saveUser(SignupDto signupDto) {
        var user = repository.save(mapper.toUser(signupDto, passwordEncoder.encode(signupDto.password())));
        return new ApiResponse<>(HttpStatus.OK.value(), "User successfully Signed up with ID: " + user.getId(), null);
    }

    @Override
    public ApiResponse<AuthResponse> login(LoginDto loginDto) {
        var user = repository.findByEmail(loginDto.email()).orElseThrow(() -> new RuntimeException("User not found"));
        return new ApiResponse<>(HttpStatus.OK.value(), "login successful", new AuthResponse( user.getId(), user.getFirstname(), user.getLastname(), utils.createJwt.apply(loadUserByUsername(user.getEmail()))));
    }

    public User getUserByEmail(String email){
        var user = repository.findByEmail(email);
        return user.get();
    }


    @Override
    public void validate(String token) {
       utils.validateToken.accept(token);
    }
}
