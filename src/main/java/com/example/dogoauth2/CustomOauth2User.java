package com.example.dogoauth2;

import lombok.Getter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class CustomOauth2User extends DefaultOAuth2User {
    private final String accessToken;

    public CustomOauth2User(OAuth2User oAuth2User, String nameAttrKey, String accessToken) {
        super(oAuth2User.getAuthorities(), oAuth2User.getAttributes(), nameAttrKey);
        this.accessToken = accessToken;
    }
}
