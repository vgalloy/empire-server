package com.vgalloy.empire.webservice.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.webservice.dto.ErrorDto;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
@Component
final class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    /**
     * Constructor.
     *
     * @param objectMapper the json mapper
     */
    CustomBasicAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {
        //Authentication failed, send error response.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(HttpStatus.UNAUTHORIZED.value());
        errorDto.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        String errorAsString = objectMapper.writeValueAsString(errorDto);

        // TODO : clean it
        PrintWriter writer = response.getWriter();
        writer.println(errorAsString);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO : correct realm
        setRealmName("REALM_TEST");
        super.afterPropertiesSet();
    }
}
