package com.vgalloy.empire.webservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vincent Galloy
 * Created by Vincent Galloy on 28/02/18.
 */
public class RequestTimerFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Tumbleweed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            long total = start - System.currentTimeMillis();
            LOGGER.info("Total execution time : " + total + " ms");
        }
    }

    @Override
    public void destroy() {
        // Tumbleweed
    }
}
