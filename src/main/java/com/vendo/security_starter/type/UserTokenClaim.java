package com.vendo.security_starter.type;

public enum UserTokenClaim {

    ID("id"),
    VERIFIED("verified"),
    ROLES("roles"),
    STATUS("status");

    private final String claim;

    UserTokenClaim(String claim) {
        this.claim = claim;
    }

    public String getClaim() {
        return this.claim;
    }

}
