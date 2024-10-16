package com.ofektom.authentication_service.dto;

public record SignupDto(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
