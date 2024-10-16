package com.ofektom.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperResponse {
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("api_key")
    private String apiKey;
}
