package com.smartcampus.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import org.springframework.security.web.authentication.
        UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter
            jwtAuthenticationFilter;

    private final JwtAuthenticationEntryPoint
            authenticationEntryPoint;

    private final CustomAccessDeniedHandler
            accessDeniedHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            JwtAuthenticationEntryPoint authenticationEntryPoint,
            CustomAccessDeniedHandler accessDeniedHandler
    ) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;

        this.authenticationEntryPoint =
                authenticationEntryPoint;

        this.accessDeniedHandler =
                accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(exception ->

                        exception

                                .authenticationEntryPoint(
                                        authenticationEntryPoint
                                )

                                .accessDeniedHandler(
                                        accessDeniedHandler
                                )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        .requestMatchers(
                                "/api/users/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/api/students/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .requestMatchers(
                                "/api/courses/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .requestMatchers(
                                "/api/enrollments/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .requestMatchers(
                                "/api/attendance/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .requestMatchers(
                                "/api/payments/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .requestMatchers(
                                "/api/hostel-rooms/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .requestMatchers(
                                "/api/faculties/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )
                        .requestMatchers(
                                "/api/dashboard/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "FACULTY"
                        )

                        .anyRequest()
                        .authenticated()
                )

                .httpBasic(httpBasic ->
                        httpBasic.disable()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}