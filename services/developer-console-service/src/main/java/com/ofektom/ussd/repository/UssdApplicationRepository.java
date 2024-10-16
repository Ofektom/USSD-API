package com.ofektom.ussd.repository;

import com.ofektom.ussd.entity.UssdApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UssdApplicationRepository extends JpaRepository<UssdApplication, Long> {
    Optional<UssdApplication> findByApiKey(String apiKey);
}

