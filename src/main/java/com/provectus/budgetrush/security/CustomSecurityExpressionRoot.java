package com.provectus.budgetrush.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public boolean adminOnly() {
        log.debug("haha -- check if this function is used by admin role only");
        return this.hasAuthority("ADMIN");
    }

    public CustomSecurityExpressionRoot(Authentication a) {
        super(a);
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

}
