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
import com.provectus.budgetrush.data.User;
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
        User user = service.find(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        Preconditions.checkNotNull(user, "Bad User Credentials.");

        return new UserAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), getUserAuthorities(user));

    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getUserAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        GrantedAuthority authority = new UserAuthority(user.getRole());
        authorities.add(authority);

        return authorities;
    }
}
