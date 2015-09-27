package com.provectus.budgetrush.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        User user = getUserFromObject(targetDomainObject);

        String userName = authentication.getName();
        log.info("Check user " + userName);
        return userName.equals(user.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new RuntimeException("Id and Class permissions are not supperted by this application");
    }

    private User getUserFromObject(Object object) {
        if (object instanceof User) {
            return (User) object;
        } else if (object instanceof Account) {
            return ((Account) object).getUser();
        } else if (object instanceof Category) {
            return ((Category) object).getUser();
        } else if (object instanceof Contractor) {
            return ((Contractor) object).getUser();
        } else if (object instanceof Order) {
            return ((Order) object).getAccount().getUser();
        } else {
            throw new RuntimeException("Unown class.");
        }

    }
}
