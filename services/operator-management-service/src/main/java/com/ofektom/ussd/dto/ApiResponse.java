package com.ofektom.ussd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {
    @JsonProperty("status_code")
    private int statusCode;
    private String message;
    private T data;
}
