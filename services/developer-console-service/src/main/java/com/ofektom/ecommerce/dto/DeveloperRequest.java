package com.ofektom.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperRequest {
    private String appName;
    private String callbackUrl;
    private boolean active;
}
