package com.provectus.budgetrush.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Preconditions;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Preconditions.checkNotNull(authentication, "Authentication equals null.");

        log.info("Start check user " + authentication.getPrincipal().toString());
        boolean validUser = service.isValidUser(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        Preconditions.checkState(validUser, "Bad User Credentials.");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new GrantedAuthority() {

            private static final long serialVersionUID = 1L;

            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        });
        return new UserAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities);

    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
