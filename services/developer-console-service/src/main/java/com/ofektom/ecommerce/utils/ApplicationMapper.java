package com.ofektom.ecommerce.utils;

import com.ofektom.ecommerce.dto.DeveloperRequest;
import com.ofektom.ecommerce.dto.DeveloperResponse;
import com.ofektom.ecommerce.entity.UssdApplication;
import org.springframework.http.HttpStatus;

public class ApplicationMapper {
    public UssdApplication toApplication(DeveloperRequest developerRequest, String apiKey) {
        return null;
    }

    public DeveloperResponse fromApplication(UssdApplication application) {
       return new DeveloperResponse(
                HttpStatus.OK.value(),
                application.getApiKey()
        );
    }
}
