package com.ofektom.authentication_service.config;

import com.ofektom.authentication_service.service.impl.AuthServiceImpl;
import com.ofektom.authentication_service.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter filter;
    private final AuthServiceImpl service;

    public SecurityConfig(JwtAuthenticationFilter filter, @Lazy AuthServiceImpl service) {
        this.filter = filter;
        this.service = service;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(service);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(httpRequests ->
                        httpRequests
                                .requestMatchers(
                                        "/","/api/v1/auth/**", "/api/v1/customers/create"
                                ).permitAll()
                                .requestMatchers(
                                        "/api/v1/products/create",
                                        "/api/v1/customers/all",
                                        "/api/v1/orders/all",
                                        "/api/v1/payments/all",
                                        "/api/v1/ratings/all",
                                        "/api/v1/order-lines/**",
                                        "/api/v1/ratings/product/{productId}",
                                        "api/v1/ratings/product/{productId}/average",
                                        "/api/v1/customers/delete/{customer-id}").hasAnyRole("ADMIN")
                                .requestMatchers(
                                        "/api/v1/products/all",
                                        "/api/v1/customers/{customer-id}",
                                        "/api/v1/customers/update",
                                        "/api/v1/orders/create",
                                        "/api/v1/orders/{order-id}",
                                        "/api/v1/orders/customer/{customer-id}",
                                        "/api/v1/products/{product-id}").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers(
                                        "/api/v1/ratings/add",
                                        "/api/v1/recommenders/recommendation").hasRole("CUSTOMER")
                                .anyRequest().authenticated())
                .logout(logout -> logout
                        .deleteCookies("remove")
                        .invalidateHttpSession(true)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
