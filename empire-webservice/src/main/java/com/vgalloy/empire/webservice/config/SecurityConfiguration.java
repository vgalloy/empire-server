package com.vgalloy.empire.webservice.config;

import java.util.Objects;

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
    public SecurityConfiguration(final BasicAuthenticationEntryPoint basicAuthenticationEntryPoint, final UserDetailsService userDetailsService) {
        this.basicAuthenticationEntryPoint = Objects.requireNonNull(basicAuthenticationEntryPoint);
        this.userDetailsService = Objects.requireNonNull(userDetailsService);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/empires/**").authenticated()
            .and()
            .httpBasic().authenticationEntryPoint(basicAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring()
            .antMatchers("/v2/api-docs")
            .antMatchers("/swagger-ui.html");
    }
}
