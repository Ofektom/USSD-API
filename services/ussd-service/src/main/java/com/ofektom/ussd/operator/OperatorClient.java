package com.ofektom.ussd.operator;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OperatorClient {
    private final RestTemplate restTemplate;
    @Value("${application.config.operator-url}")
    private String operatorUrl;

    public UssdResponse handleUssdRequest(UssdRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UssdRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<UssdResponse> response = restTemplate.exchange(operatorUrl, HttpMethod.POST, entity, UssdResponse.class);

        return response.getBody();
    }
}
