package com.ofektom.ussd.service;

import com.ofektom.ussd.dto.DeveloperRequest;
import com.ofektom.ussd.dto.DeveloperResponse;
import com.ofektom.ussd.entity.UssdApplication;

public interface DeveloperApplicationService {
    DeveloperResponse registerApplication(DeveloperRequest developerRequest);
    UssdApplication getApplication(String apiKey);

    DeveloperResponse updateApplication(String apiKey, DeveloperRequest request);
}
