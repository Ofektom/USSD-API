package com.ofektom.authentication_service.repository;

import com.ofektom.authentication_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
