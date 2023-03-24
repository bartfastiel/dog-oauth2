package com.example.dogoauth2;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomAuthClientService implements OAuth2AuthorizedClientService {

    private final InMemoryOAuth2AuthorizedClientService delegate;

    CustomAuthClientService(ClientRegistrationRepository clientRegistrationRepository) {
        this.delegate = new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return delegate.loadAuthorizedClient(clientRegistrationId, principalName);
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        delegate.saveAuthorizedClient(authorizedClient, principal);
        System.out.println("GOT TOKEN! " + authorizedClient.getAccessToken().getTokenValue());
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        delegate.removeAuthorizedClient(clientRegistrationId, principalName);
    }
}
