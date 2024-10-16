package com.ofektom.ussd.service.impl;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.service.OperatorManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NineMobileGateway implements OperatorManagementService {
    @Value("${nine-mobile.api.url}")
    private String nineMobileApiUrl;
    private final RestTemplate restTemplate;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NineMobileGateway(RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getOperatorCode() {
        // Query the operator code from a database
        String sql = "SELECT operator_code FROM operator_info WHERE operator_name = '9Mobile'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @Override
    public UssdResponse handleRequest(UssdRequest request) {
        ResponseEntity<UssdResponse> response = restTemplate.postForEntity(nineMobileApiUrl, request, UssdResponse.class);
        return response.getBody();
    }
}
