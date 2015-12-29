package com.provectus.budgetrush.security;

import com.google.common.base.Preconditions;
import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.budget.Budget;
import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.data.contractor.Contractor;
import com.provectus.budgetrush.data.group.Group;
import com.provectus.budgetrush.data.order.Order;
import com.provectus.budgetrush.data.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import java.util.Set;

@Slf4j
public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public CustomSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean adminOnly() {
        return this.hasAuthority("ROLE_ADMIN");
    }

    public boolean isObjectOwnerOrAdmin(Object object, Object permission) {
        if (this.hasAuthority("ROLE_ADMIN")) {
            return true;
        }

        return hasPermission((User)getUserFromObject(object), permission);
    }

    public boolean isObjectOwnerOrAdminOrAll(Category category, Object permission) {
        if (this.hasAuthority("ROLE_ADMIN")) {
            return true;
        } else if (category.isPredefined()) {
            return true;
        }

        return hasPermission((Object) category, permission);
    }

    public boolean isObjectOwnerOrAdminForUpdateAndDelete(Category category, Object permission) {
        if (category.isPredefined()) {
            return false;
        }
        if (this.hasAuthority("ROLE_ADMIN")) {
            return true;
        }


        return hasPermission((Object) category, permission);
    }

    public boolean inGroupOrAdmin(Object object, Object permission) {
        if (this.hasAuthority("ROLE_ADMIN")) {
            return true;
        }

        Set<User> users = null;

       if (object instanceof Group) {
            users = ((Group) object).getUsers();
        } else if (object instanceof Order) {
            users = ((Order) object).getAccount().getGroup().getUsers();
        } else if (object instanceof Budget) {
            users = ((Budget) object).getGroup().getUsers();
        } else if (object instanceof Account) {
            users = ((Account) object).getGroup().getUsers();
        } else

            Assert.notEmpty(users, "Empty group in " + (object));

        for (User user : users) {
            if (hasPermission(user, permission)) {
                return true;
            }
        }

        return false;

    }

    public boolean changeRolePermission(Object object) {

        if (this.hasAuthority("ROLE_ADMIN")) {
            return true;
        }

        User user = (User) object;

        Preconditions.checkArgument(this.hasAuthority(user.getRole().name()), "You have not permission to change role");

        return true;
    }

    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    public Object getFilterObject() {
        return filterObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    public Object getThis() {
        return target;
    }


    private User getUserFromObject(Object object) {

        if (object instanceof User) {
            return (User) object;
        }
        if (object instanceof Category) {
            return (User) ((Category) object).getUser();
        }
        if (object instanceof Contractor) {
            return (User) ((Contractor) object).getUser();
        }
        if (object instanceof Budget) {
            return (User) ((Budget) object).getCategory().getUser();
        }
        if (object instanceof Order) {
            return (User) ((Order) object).getCategory().getUser();
        }

            throw new RuntimeException("Unknown class. " + object);

    }

}
