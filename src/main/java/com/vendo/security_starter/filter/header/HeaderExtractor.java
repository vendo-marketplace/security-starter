package com.vendo.security_starter.filter.header;

import jakarta.servlet.http.HttpServletRequest;

public interface HeaderExtractor {

    String require(String header, HttpServletRequest request) throws IllegalArgumentException;

}
