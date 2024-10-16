package com.ofektom.authentication_service.utils;

import com.ofektom.authentication_service.dto.AuthResponse;
import com.ofektom.authentication_service.dto.SignupDto;
import com.ofektom.authentication_service.entity.User;
import com.ofektom.authentication_service.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    public User toUser(SignupDto signupDto, String password) {
        return User.builder()
                .firstname(signupDto.firstname())
                .lastname(signupDto.lastname())
                .email(signupDto.email())
                .password(password)
                .role(Role.ROLE_CUSTOMER)
                .build();
    }

    public AuthResponse fromUser(User user, String token) {
        return new AuthResponse(
               user.getId(),
               user.getFirstname(),
               user.getLastname(),
               token
        );
    }
}
