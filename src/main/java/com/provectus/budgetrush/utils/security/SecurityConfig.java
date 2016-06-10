package com.provectus.budgetrush.utils.security;


import com.provectus.budgetrush.security.CustomSecurityExpressionHandler;
import com.provectus.budgetrush.security.UserAuthenticationProvider;
import com.provectus.budgetrush.security.UserPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class SecurityConfig {

    @Autowired
    private DriverManagerDataSource dataSource;

    @Bean
    public UserPermissionEvaluator permissionEvaluator(){
        return new UserPermissionEvaluator();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public CustomSecurityExpressionHandler expressionHandler (){
        CustomSecurityExpressionHandler expressionHandler = new CustomSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(permissionEvaluator());
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public UserAuthenticationProvider customUserAuthenticationProvider(){
        return new UserAuthenticationProvider();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public JdbcClientDetailsService clientDetails(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public ClientDetailsUserDetailsService clientDetailsUserService(){
        return new ClientDetailsUserDetailsService(clientDetails());
    }

    //Oauth2

    @Bean
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint(){
        return new OAuth2AuthenticationEntryPoint();
    }

    @Bean
    public OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint(){
        OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
        entryPoint.setRealmName("springsec/client");
        entryPoint.setTypeName("Basic");
        return entryPoint;
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler(){
        return new OAuth2AccessDeniedHandler();
    }

//    @Bean
//    public ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter(){
//
//    }

    @Bean
    public JdbcTokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(1800);
        return tokenServices;
    }
}
