package com.example.dogoauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {

    private final OAuth2AuthorizedClientService clientService;

    @GetMapping
    public String getDog() {
        return clientService.loadAuthorizedClient("github", getPrincipalName())
                .getAccessToken()
                .getTokenValue();
    }

    private static String getPrincipalName() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof DefaultOAuth2User user) {
            return Objects.toString(user.getAttribute("id"));
        }
        throw new IllegalStateException("No principal found");
    }
}
