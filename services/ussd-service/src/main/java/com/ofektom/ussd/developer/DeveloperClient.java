package com.ofektom.ussd.developer;

import com.ofektom.ussd.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DeveloperClient {
    @Value("${application.config.developer-url}")
    private String developerUrl;
    private final RestTemplate restTemplate;



    public UssdApplication getApplication(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> requestEntity = new HttpEntity<>(apiKey, headers);

        ResponseEntity<UssdApplication> responseEntity = restTemplate.exchange(
                developerUrl + "/" + apiKey,
                HttpMethod.GET,
                requestEntity,
                UssdApplication.class
        );

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new BusinessException("An error occurred while checking if email exists: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
