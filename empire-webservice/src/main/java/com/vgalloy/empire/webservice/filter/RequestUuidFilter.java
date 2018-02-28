package com.vgalloy.empire.webservice.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author Vincent Galloy
 * Created by Vincent Galloy on 28/02/18.
 */
public class RequestUuidFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUuidFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Tumbleweed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUuid = UUID.randomUUID().toString();
        MDC.put("requestUuid", requestUuid);
        LOGGER.info("add correlation Id : {}", requestUuid);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Tumbleweed
    }
}
