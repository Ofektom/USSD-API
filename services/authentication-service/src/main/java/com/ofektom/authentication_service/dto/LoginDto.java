package com.ofektom.authentication_service.dto;

public record LoginDto(
        String email,
        String password
) {
}
