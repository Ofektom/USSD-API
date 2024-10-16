package com.ofektom.ussd.dto;

import lombok.Data;

@Data
public class OperatorRequest {
    private String name;
    private String countryCode;
    private String networkCode;
    private String ussdGateway;
}
