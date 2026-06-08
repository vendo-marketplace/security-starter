package com.vendo.security_starter.type;

public enum UserHeader {

    ID("X-Id"),
    EMAIL("X-Email"),
    STATUS("X-Status"),
    ROLES("X-Roles"),
    EMAIL_VERIFIED("X-Email-Verified");

    private final String header;

    UserHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}