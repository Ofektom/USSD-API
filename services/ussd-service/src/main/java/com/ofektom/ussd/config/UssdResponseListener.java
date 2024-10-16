//package com.ofektom.ussd.config;
//
//import com.ofektom.ussd.dto.UssdRequest;
//import com.ofektom.ussd.dto.UssdResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UssdResponseListener {
//    private final KafkaTemplate<String, UssdRequest> kafkaTemplate;
//
//    @KafkaListener(topics = "ussd-requests", groupId = "ussd-group")
//    public void listen(UssdRequest request) {
//        // Process the incoming request and send a response back
//        UssdResponse response = processRequest(request);
//
//        // Send response back to USSD service
//        kafkaTemplate.send("ussd-requests", request);
//    }
//
//    private UssdResponse processRequest(UssdRequest request) {
//        // Logic to handle the USSD request
//        return new UssdResponse(request.sessionId(), "Processed Request: " + request.getMessage());
//    }
//}
//
