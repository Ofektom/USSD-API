package com.ofektom.ussd.service.impl;

import com.ofektom.ussd.dto.DeveloperRequest;
import com.ofektom.ussd.dto.DeveloperResponse;
import com.ofektom.ussd.entity.UssdApplication;
import com.ofektom.ussd.repository.UssdApplicationRepository;
import com.ofektom.ussd.service.DeveloperApplicationService;
import com.ofektom.ussd.utils.ApplicationMapper;
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
    public UssdApplication getApplication(String apiKey) {
        return repository.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("App not found"));
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
