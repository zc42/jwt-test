package com.zc.tests.jwt.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class SecurityFilterChainConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] requestMatchers = getRequestMatchers();
        return http
                .csrf(AbstractHttpConfigurer::disable) // Use lambda to explicitly disable CSRF
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/jwt", "/login", "/*", "/actuator/health")
                                .requestMatchers(requestMatchers)
                                .permitAll() // Update for newer API
                                .anyRequest().authenticated() // All other endpoints require authentication
                )
                .build();
    }

    public static String[] getRequestMatchers() {
        List<String> apiMatchers = List.of("/*", "/file");
        List<String> swaggerMatchers = List.of("/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*");
        List<String> actuators = Stream.of(
                        "auditevents",
                        "beans",
                        "caches",
                        "conditions",
                        "configprops",
                        "env",
                        "health",
                        "info",
                        "loggers",
                        "metrics",
                        "mappings",
                        "shutdown",
                        "threaddump",
                        "httptrace",
                        "integrationgraph",
                        "liquibase",
                        "flyway",
                        "heapdump",
                        "sessions",
                        "serviceregistry")
                .map(e -> "/actuator/" + e)
                .toList();

        List<String> allRequestMatchers = new ArrayList<>();
        allRequestMatchers.addAll(apiMatchers);
        allRequestMatchers.addAll(swaggerMatchers);
        allRequestMatchers.addAll(actuators);
        return allRequestMatchers.toArray(new String[0]);
    }
}
