package com.web.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "my_rest_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.anonymous().disable().requestMatchers().antMatchers("/api/accounts/**")
                .requestMatchers().antMatchers("/api/accounts")
                .requestMatchers().antMatchers("/api/accounts/username/**")
                .requestMatchers().antMatchers("/api/changepassword/**")
                .requestMatchers().antMatchers("/api/accounts/changerole/**")
                .requestMatchers().antMatchers("/api/species/list/share")
                .requestMatchers().antMatchers("/api/species/share")
                .requestMatchers().antMatchers("/api/species/approve")
                .requestMatchers().antMatchers("/api/species/list/approve")
                .and().authorizeRequests()
                .antMatchers("/api/accounts/**")
                .access("hasRole('Admin') OR hasRole('Member') OR hasRole('Expert')")
                .antMatchers("/api/accounts")
                .access("hasRole('Admin')")
                .antMatchers("/api/accounts/username/**")
                .access("hasRole('Admin') OR hasRole('Member') OR hasRole('Expert')")
                .antMatchers("/api/accounts/changepassword/**")
                .access("hasRole('Admin') OR hasRole('Member') OR hasRole('Expert')")
                .antMatchers("/api/accounts/changerole/**")
                .access("hasRole('Admin') OR hasRole('Member') OR hasRole('Expert')")
                .antMatchers("/api/species/list/share")
                .access("hasRole('Member')")
                .antMatchers("/api/species/share")
                .access("hasRole('Member')")
                .antMatchers("/api/lockaccount/")
                .access("hasRole('Admin') OR hasRole('Expert')")
                .antMatchers("/api/species/approve")
                .access("hasRole('Expert')")
                .antMatchers("/api/species/list/approve")
                .access("hasRole('Expert')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}