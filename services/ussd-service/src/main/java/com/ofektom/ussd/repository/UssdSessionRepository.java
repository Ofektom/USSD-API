package com.ofektom.ussd.repository;

import com.ofektom.ussd.entity.UssdSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UssdSessionRepository extends JpaRepository<UssdSession, Long> {
    Optional<UssdSession> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
