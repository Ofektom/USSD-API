package com.ofektom.ussd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UssdResponse {
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("response_message")
    private String responseMessage;
}
