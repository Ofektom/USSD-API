package com.ofektom.ussd.controller;

import com.ofektom.ussd.dto.DeveloperRequest;
import com.ofektom.ussd.dto.DeveloperResponse;
import com.ofektom.ussd.entity.UssdApplication;
import com.ofektom.ussd.service.DeveloperApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class UssdApplicationController {

    private final DeveloperApplicationService service;

    @PostMapping("/register")
    public ResponseEntity<DeveloperResponse> registerApplication(@RequestBody DeveloperRequest developerRequest) {
        return ResponseEntity.ok(service.registerApplication(developerRequest)); // Return the API key
    }

    @GetMapping("/{apiKey}")
    public ResponseEntity<UssdApplication> getApplication(@PathVariable String apiKey) {
        return ResponseEntity.ok(service.getApplication(apiKey));
    }

    @PutMapping("/{apiKey}")
    public ResponseEntity<DeveloperResponse> updateApplication(@PathVariable String apiKey, @RequestBody DeveloperRequest request) {
        return ResponseEntity.ok(service.updateApplication(apiKey, request));
    }
}

