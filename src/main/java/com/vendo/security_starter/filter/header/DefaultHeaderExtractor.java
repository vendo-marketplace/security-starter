package com.vendo.security_starter.filter.header;

import com.vendo.utils_lib.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultHeaderExtractor implements HeaderExtractor {

    @Override
    public String require(String header, HttpServletRequest request) throws IllegalArgumentException {
        String value = request.getHeader(header);

        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("Required header %s is missing.".formatted(header));
        }

        return value;
    }
}

