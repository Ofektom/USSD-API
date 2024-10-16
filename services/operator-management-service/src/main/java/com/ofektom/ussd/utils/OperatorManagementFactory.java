package com.ofektom.ussd.utils;

import com.ofektom.ussd.service.OperatorManagementService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperatorManagementFactory {

    private final Map<String, OperatorManagementService> operatorServices = new HashMap<>();

    public OperatorManagementFactory(List<OperatorManagementService> operatorManagementServices) {
        for (OperatorManagementService service : operatorManagementServices) {
            operatorServices.put(service.getOperatorCode().toLowerCase(), service);
        }
    }

    public OperatorManagementService getOperatorService(String operatorCode) {
        return operatorServices.get(operatorCode.toLowerCase());
    }
}

