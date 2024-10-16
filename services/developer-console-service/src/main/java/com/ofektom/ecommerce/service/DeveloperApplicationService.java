package com.ofektom.ecommerce.service;

import com.ofektom.ecommerce.dto.DeveloperRequest;
import com.ofektom.ecommerce.dto.DeveloperResponse;

public interface DeveloperApplicationService {
    DeveloperResponse registerApplication(DeveloperRequest developerRequest);
    DeveloperResponse getApplication(String apiKey);

    DeveloperResponse updateApplication(String apiKey, DeveloperRequest request);
}
