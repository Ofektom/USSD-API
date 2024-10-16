package com.ofektom.ussd.utils;

import com.ofektom.ussd.dto.DeveloperRequest;
import com.ofektom.ussd.dto.DeveloperResponse;
import com.ofektom.ussd.entity.UssdApplication;
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
