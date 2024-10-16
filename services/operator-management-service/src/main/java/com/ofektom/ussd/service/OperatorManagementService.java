package com.ofektom.ussd.service;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;

public interface OperatorManagementService {
    String getOperatorCode();
    UssdResponse handleRequest(UssdRequest request);
}