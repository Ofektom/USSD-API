package com.ofektom.ecommerce.service.impl;

import com.ofektom.ecommerce.dto.DeveloperRequest;
import com.ofektom.ecommerce.dto.DeveloperResponse;
import com.ofektom.ecommerce.entity.UssdApplication;
import com.ofektom.ecommerce.repository.UssdApplicationRepository;
import com.ofektom.ecommerce.service.DeveloperApplicationService;
import com.ofektom.ecommerce.utils.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeveloperApplicationServiceImpl implements DeveloperApplicationService {
    private final UssdApplicationRepository repository;
    private final ApplicationMapper mapper;

    @Override
    public DeveloperResponse registerApplication(DeveloperRequest developerRequest){
        String apiKey = UUID.randomUUID().toString();
        UssdApplication application = repository.save(mapper.toApplication(developerRequest, apiKey));
        return mapper.fromApplication(application);
    }

    @Override
    public DeveloperResponse getApplication(String apiKey) {
        UssdApplication app = repository.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("App not found"));
        return mapper.fromApplication(app);
    }

    @Override
    public DeveloperResponse updateApplication(String apiKey, DeveloperRequest request) {
        UssdApplication app = repository.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("App not found"));
        app.setAppName(request.getAppName());
        app.setCallbackUrl(request.getCallbackUrl());
        app.setActive(request.isActive());
        return mapper.fromApplication(repository.save(app));
    }
}
