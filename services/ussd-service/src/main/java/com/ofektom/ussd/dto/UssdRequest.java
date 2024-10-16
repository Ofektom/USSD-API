package com.ofektom.ussd.dto;

public record UssdRequest(
         String sessionId,
         String phoneNumber,
         String operator,
         String shortcode,
         String message
) {
}