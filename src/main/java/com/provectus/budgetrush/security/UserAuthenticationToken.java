package com.provectus.budgetrush.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 4077726713424012376L;
    private final Object principal;
    private Object credentials;

    public UserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
