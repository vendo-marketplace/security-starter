package com.vendo.security_starter.filter.header;

import com.vendo.utils_lib.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultHeadersExtractor implements HeaderExtractor {

    private final HttpServletRequest request;

    public DefaultHeadersExtractor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String require(String header) throws IllegalArgumentException {
        String value = request.getHeader(header);

        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("Required header %s is missing.".formatted(header));
        }

        return value;
    }
}

