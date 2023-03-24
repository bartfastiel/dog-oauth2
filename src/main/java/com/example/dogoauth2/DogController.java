package com.example.dogoauth2;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    @GetMapping
    public String getDog() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomOauth2User) {
            return "Access Token: " + ((CustomOauth2User) principal).getAccessToken();
        }
        return "Woof";
    }
}
