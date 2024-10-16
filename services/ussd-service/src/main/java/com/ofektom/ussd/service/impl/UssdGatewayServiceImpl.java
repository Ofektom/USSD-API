package com.ofektom.ussd.service.impl;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.entity.UssdApplication;
import com.ofektom.ussd.entity.UssdSession;
import com.ofektom.ussd.operator.OperatorClient;
import com.ofektom.ussd.repository.UssdApplicationRepository;
import com.ofektom.ussd.repository.UssdSessionRepository;
import com.ofektom.ussd.service.UssdGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UssdGatewayServiceImpl implements UssdGatewayService {

    private final UssdSessionRepository sessionRepository;
    private final UssdApplicationRepository applicationRepository;
    private final OperatorClient operatorClient;


    @Override
    public UssdResponse processUssdRequest(UssdRequest request, String apiKey) {

        // Validate the application
        UssdApplication application = applicationRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("Invalid API Key"));

        // Find or create session
        UssdSession session = sessionRepository.findBySessionId(request.sessionId())
                .orElseGet(() -> startNewSession(request, application));

        // Update last activity time
        session.setLastActivityTime(LocalDateTime.now());
        sessionRepository.save(session);

        // Update last activity time
        session.setLastActivityTime(LocalDateTime.now());
        sessionRepository.save(session);

         // URL to Operator Management Service
        UssdResponse operatorResponse = operatorClient.handleUssdRequest(request);

        // Optionally forward response to the application's callback URL
        forwardToApplication(application, operatorResponse);
        return operatorResponse;
    }

    private UssdSession startNewSession(UssdRequest request, UssdApplication application) {
        UssdSession session = new UssdSession();
        session.setSessionId(request.sessionId());
        session.setPhoneNumber(request.phoneNumber());
        session.setNetworkOperator(request.operator());
        session.setShortcode(request.shortcode());
        session.setSessionStartTime(LocalDateTime.now());
        session.setLastActivityTime(LocalDateTime.now());
        session.setApplicationId(application.getApiKey());
        session.setSessionStatus("ACTIVE");
        return sessionRepository.save(session);
    }

    private void forwardToApplication(UssdApplication application, UssdResponse response) {
        if (application.getCallbackUrl() != null && !application.getCallbackUrl().isEmpty()) {
            // Call the callback URL with the response
            // Use RestTemplate to POST the response to the application's URL
        }
    }

    @Override
    public void clearSession(String sessionId) {
        sessionRepository.deleteBySessionId(sessionId);
    }

    @Override
    public void clearExpiredSessions() {
        // Implement session cleanup logic based on inactivity
    }
}

