package com.provectus.budgetrush.security;

import com.provectus.budgetrush.data.budget.Budget;
import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.data.contractor.Contractor;
import com.provectus.budgetrush.data.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

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
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        throw new RuntimeException("Id and Class permissions are not supported by this application");
    }

    private User getUserFromObject(Object object) {
        if (object instanceof User) {
            return (User) object;
        } else if (object instanceof Category) {
            return ((Category) object).getUser();
        } else if (object instanceof Contractor) {
            return ((Contractor) object).getUser();
        } else if (object instanceof Budget) {
            return ((Budget) object).getCategory().getUser();
        } else {
            throw new RuntimeException("Unknown class.");
        }
    }
}
