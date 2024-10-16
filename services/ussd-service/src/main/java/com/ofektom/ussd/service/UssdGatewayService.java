package com.ofektom.ussd.service;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;

public interface UssdGatewayService {

    UssdResponse processUssdRequest(UssdRequest request, String apiKey);
    void clearSession(String sessionId);
    void clearExpiredSessions();
}

