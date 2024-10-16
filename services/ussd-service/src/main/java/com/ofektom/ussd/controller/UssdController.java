package com.ofektom.ussd.controller;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.service.impl.UssdGatewayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ussd")
@RequiredArgsConstructor
public class UssdController {

    private final UssdGatewayServiceImpl ussdGatewayService;

    @PostMapping("/process")
    public ResponseEntity<UssdResponse> processUssd(@RequestBody UssdRequest request, @RequestHeader("Api-Key") String apiKey) {
        UssdResponse response = ussdGatewayService.processUssdRequest(request, apiKey);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Void> clearSession(@PathVariable String sessionId) {
        ussdGatewayService.clearSession(sessionId);
        return ResponseEntity.noContent().build();
    }
}

