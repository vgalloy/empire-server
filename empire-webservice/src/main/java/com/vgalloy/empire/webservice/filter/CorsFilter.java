package com.vgalloy.empire.webservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent Galloy on 20/08/17.
 * Allow all request to access the resources of the application
 *
 * @author Vincent Galloy
 */
@Component
final class CorsFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // Tumbleweed
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, *");

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Tumbleweed
    }
}
