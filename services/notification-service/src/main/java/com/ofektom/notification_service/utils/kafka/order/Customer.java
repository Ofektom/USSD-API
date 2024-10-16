package com.ofektom.notification_service.utils.kafka.order;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
