package com.provectus.budgetrush.security;

import org.springframework.security.core.GrantedAuthority;

import com.provectus.budgetrush.data.Roles;

class UserAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    private Roles role;

    public UserAuthority(Roles role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }

}