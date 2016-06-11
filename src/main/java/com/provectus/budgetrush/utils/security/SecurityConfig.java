package com.provectus.budgetrush.utils.security;


import com.provectus.budgetrush.security.CustomSecurityExpressionHandler;
import com.provectus.budgetrush.security.UserAuthenticationProvider;
import com.provectus.budgetrush.security.UserPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DriverManagerDataSource dataSource;
    @Autowired
    @Qualifier("resourceServerFilter")
    private Filter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        <http pattern="/v1/**" create-session="never"
//        authentication-manager-ref="authenticationManager"
//        entry-point-ref="oauthAuthenticationEntryPoint"
//        xmlns="http://www.springframework.org/schema/security">
//        <intercept-url pattern="/v1/**" requires-channel="https"/>
//        <custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER" />
//        <access-denied-handler ref="oauthAccessDeniedHandler" />
//        <csrf disabled="true" />
//        <headers>
//        <hsts
//        include-subdomains="true"
//        max-age-seconds=" 63072000" />
//        </headers>
//        </http>
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authenticationProvider(customUserAuthenticationProvider())
                .userDetailsService(userDetailsService())
                .requiresChannel()
                .and()
                .antMatcher("/v1/**")
                .authorizeRequests()
                .and()
                .addFilterBefore(filter, null)
                .exceptionHandling()
                .accessDeniedHandler(oauthAccessDeniedHandler())
                .and()
                .headers().addHeaderWriter(new HstsHeaderWriter(63072000, true))
                .configure(http);
    }

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

  @Bean
    public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository(){
      HttpSessionSecurityContextRepository securityContextRepository =
              new HttpSessionSecurityContextRepository();
      securityContextRepository.setAllowSessionCreation(false);
      return securityContextRepository;
  }
    @Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilter(){
        return new SecurityContextPersistenceFilter(httpSessionSecurityContextRepository());
    }

    @Bean
    public FilterChainProxy filterChainProxy(){

        return new FilterChainProxy(
                new DefaultSecurityFilterChain(
                        new AntPathRequestMatcher("/v1/**"),
                        new SecurityContextPersistenceFilter()));
    }
}
