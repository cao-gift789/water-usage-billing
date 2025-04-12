package com.waterbilling.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tắt CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Cho phép tất cả request mà không cần xác thực
            );
        return http.build();
    }
}
