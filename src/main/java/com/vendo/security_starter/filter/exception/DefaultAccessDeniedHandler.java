package com.vendo.security_starter.filter.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendo.security_starter.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(DefaultAccessDeniedHandler.class);

    public DefaultAccessDeniedHandler(ObjectMapper objectMapper) {
        log.debug("Default access denied handler initialized.");
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        log.warn("Handling access denied exception: {}.", exception.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpServletResponse.SC_FORBIDDEN)
                .path(request.getRequestURI())
                .message("Resource is unreachable.")
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }
}
