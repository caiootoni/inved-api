package com.inved.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login",
            "/users"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/users/test"
    };

    public static final String[] ENDPOINTS_CUSTOMER = {
            "/users/test/customer"
    };

    public static final String[] ENDPOINTS_ADMIN = {
            "/users/test/administrator"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR") // Role ajustada
                        .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER") // Role ajustada
                        .anyRequest().denyAll()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add custom filter
                .build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

