package com.provectus.budgetrush.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler implements MethodSecurityExpressionHandler {

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        CustomSecurityExpressionRoot root = new CustomSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
