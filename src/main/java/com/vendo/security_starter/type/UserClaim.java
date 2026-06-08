package com.vendo.security_starter.type;

public enum UserClaim {

    ID("id"),
    EMAIL("email"),
    VERIFIED("verified"),
    ROLES("roles"),
    STATUS("status");

    private final String claim;

    UserClaim(String claim) {
        this.claim = claim;
    }

    public String getClaim() {
        return claim;
    }

}
