package com.ofektom.authentication_service.controller;

import com.ofektom.authentication_service.dto.ApiResponse;
import com.ofektom.authentication_service.dto.AuthResponse;
import com.ofektom.authentication_service.service.AuthService;
import com.ofektom.authentication_service.dto.LoginDto;
import com.ofektom.authentication_service.dto.SignupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@RequestBody SignupDto signupDto){
        return ResponseEntity.ok(service.saveUser(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginDto loginDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
        if(authenticate.isAuthenticated()){
            return ResponseEntity.ok(service.login(loginDto));
        }else {
            throw new BadCredentialsException("email or password not correct");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam ("token") String token){
        service.validate(token);
        return ResponseEntity.ok("Valid token");
    }

}
