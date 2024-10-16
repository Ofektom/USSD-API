package com.ofektom.notification_service.service;

import com.ofektom.notification_service.utils.kafka.order.OrderConfirmation;
import com.ofektom.notification_service.utils.kafka.payment.PaymentConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "order-confirmation-topic", groupId = "notificationGroup")
    public void consumeOrderConfirmation(ConsumerRecord<String, OrderConfirmation> record) {
        OrderConfirmation orderConfirmation = record.value();
        // Process order confirmation message
        log.info("Received Order Confirmation: {}", orderConfirmation);
    }

    @KafkaListener(topics = "payment-confirmation-topic", groupId = "notificationGroup")
    public void consumePaymentConfirmation(ConsumerRecord<String, PaymentConfirmation> record) {
        PaymentConfirmation paymentConfirmation = record.value();
        // Process payment confirmation message
        log.info("Received Payment Confirmation: {}", paymentConfirmation);
    }
}

