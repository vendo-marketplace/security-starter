package com.vendo.security_starter.filter.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendo.security_lib.exception.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationEntryPoint.class);

    public DefaultAuthenticationEntryPoint(ObjectMapper objectMapper) {
        log.debug("Default authentication entry point initialized.");
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("Handling authentication exception: {}", exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .path(request.getRequestURI())
                .message("Unauthorized.")
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }

}
