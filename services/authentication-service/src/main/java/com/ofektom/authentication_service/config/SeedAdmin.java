package com.ofektom.authentication_service.config;

import com.ofektom.authentication_service.enums.Role;
import com.ofektom.authentication_service.repository.AuthRepository;
import com.ofektom.authentication_service.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedAdmin implements CommandLineRunner {
    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (isDatabaseEmpty()) {
            seedAdmin();
        } else {
            System.out.println("Database is not empty, skipping seeding.");
        }
    }

    @Transactional(readOnly = true)
    public boolean isDatabaseEmpty() {
        return repository.count() == 0;
    }

    @Transactional
    public void seedAdmin() {
        User admin1 = new User();
        admin1.setId(UUID.randomUUID().toString());
        admin1.setFirstname("first");
        admin1.setLastname("admin");
        admin1.setEmail("admin1@ofektomtech.dev");
        admin1.setPassword(passwordEncoder.encode("Administrator@1"));
        admin1.setRole(Role.ROLE_ADMIN);

        repository.save(admin1);
    }
}
