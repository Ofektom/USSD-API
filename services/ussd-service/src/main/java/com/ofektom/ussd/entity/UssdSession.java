package com.ofektom.ussd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UssdSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private String phoneNumber;
    private String networkOperator;
    private String shortcode;
    private String lastUserInput;
    private String currentMenu;
    private LocalDateTime sessionStartTime;
    private LocalDateTime lastActivityTime;
    private String applicationId;
    private String sessionStatus;
}



