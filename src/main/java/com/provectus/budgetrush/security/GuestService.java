package com.provectus.budgetrush.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class GuestService implements ClientDetailsService {

    private String id;
    private String secretKey;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info("Start check client " + clientId);
        Preconditions.checkState(clientId.equals(id), "No client recognized with id: " + clientId);

        List<String> authorizedGrantTypes = new ArrayList<>();
        authorizedGrantTypes.add("password");
        authorizedGrantTypes.add("refresh_token");
        authorizedGrantTypes.add("client_credentials");

        List<String> authorizedScopes = new ArrayList<>();
        authorizedScopes.add("read");
        authorizedScopes.add("wright");
        authorizedScopes.add("trust");
        List<GrantedAuthority> authorities = new ArrayList<>();

        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(id);
        clientDetails.setClientSecret(secretKey);
        clientDetails.setAuthorities(authorities);
        clientDetails.setScope(authorizedScopes);
        clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);

        log.info("Client details " + clientDetails);
        return clientDetails;

    }

}
