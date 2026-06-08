package com.vendo.security_starter.filter.header;

public interface HeaderExtractor {

    String require(String header) throws IllegalArgumentException;

}
