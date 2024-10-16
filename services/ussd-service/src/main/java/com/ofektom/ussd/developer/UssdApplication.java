package com.ofektom.ussd.developer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UssdApplication {
    private Long id;
    private String appName;
    private String apiKey;
    private String callbackUrl;
    private boolean active;
}

