package com.example.dogoauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class MyOAuthClientConfiguration {

    private final CustomAuthClientService customAuthClientService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
                .oauth2Login((login) -> login.authorizedClientService(customAuthClientService))
                .build();
    }

}
