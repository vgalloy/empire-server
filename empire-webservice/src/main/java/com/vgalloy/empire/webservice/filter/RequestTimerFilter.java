package com.vgalloy.empire.webservice.filter;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent Galloy on 28/02/18.
 *
 * @author Vincent Galloy
 */
@Component
public class RequestTimerFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // Tumbleweed
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        TimerContextHolder.start();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (LOGGER.isInfoEnabled()) {
                TimerContextHolder.getExecutionTimeMillis()
                    .ifPresent(executionTime -> LOGGER.info("Total execution time : {} ms", executionTime));
            }
        }
    }

    @Override
    public void destroy() {
        // Tumbleweed
    }
}
