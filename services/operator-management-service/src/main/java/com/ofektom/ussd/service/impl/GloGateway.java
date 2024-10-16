package com.ofektom.ussd.service.impl;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.service.OperatorManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GloGateway implements OperatorManagementService {
    @Value("${glo.api.url}")
    private String gloApiUrl;
    private final RestTemplate restTemplate;

    @Override
    public String getOperatorCode() {
        return "Glo";
    }

    @Override
    public UssdResponse handleRequest(UssdRequest request) {
        ResponseEntity<UssdResponse> response = restTemplate.postForEntity(gloApiUrl, request, UssdResponse.class);
        return response.getBody();
    }
}
