package com.ofektom.authentication_service.dto;

public record AuthResponse(
        String id,
        String firstname,
        String lastname,
        String token
) {
}
