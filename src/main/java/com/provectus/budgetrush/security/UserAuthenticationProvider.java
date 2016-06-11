package com.provectus.budgetrush.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import javax.inject.Inject;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Preconditions;
import com.provectus.budgetrush.data.user.User;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Preconditions.checkNotNull(authentication, "Authentication equals null.");

        log.info("Start check user " + authentication.getPrincipal().toString());
        String hexPassword = DigestUtils.md5Hex(authentication.getCredentials().toString());
        User user = null;
        try {
            user = userService.find(authentication.getPrincipal().toString(), hexPassword);
        } catch (Exception exception) {
            log.info("Can`t find user by hex pass. " + exception);
        }
        if (user == null) {
            try {
                user = userService.find(authentication.getPrincipal().toString(),
                        authentication.getCredentials().toString());
            } catch (Exception exception) {
                log.info("Can`t find user by pass. " + exception);
            }
        }

        Preconditions.checkNotNull(user, "Wrong login or password.");

        return new UserAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
                getUserAuthorities(user));

    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getUserAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        log.info("User " + user.getName() + " has role :" + user.getRole());
        GrantedAuthority authority = new UserAuthority(user.getRole());

        authorities.add(authority);

        return authorities;
    }

}