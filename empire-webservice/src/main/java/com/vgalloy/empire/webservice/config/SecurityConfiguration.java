package com.vgalloy.empire.webservice.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor.
     *
     * @param basicAuthenticationEntryPoint the basic authentication entry point
     * @param userDetailsService            the user detail service
     */
    public SecurityConfiguration(BasicAuthenticationEntryPoint basicAuthenticationEntryPoint, UserDetailsService userDetailsService) {
        this.basicAuthenticationEntryPoint = Objects.requireNonNull(basicAuthenticationEntryPoint);
        this.userDetailsService = Objects.requireNonNull(userDetailsService);
    }

    /**
     * Configuration for {@link org.springframework.security.authentication.AuthenticationManager}.
     *
     * @param auth the builder
     * @throws Exception if an error occurs when adding the in memory authentication
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/empires/**").authenticated()
            .and()
            .httpBasic().authenticationEntryPoint(basicAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/v2/api-docs")
            .antMatchers("/swagger-ui.html");
    }
}
