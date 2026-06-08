package com.vendo.security_starter.type;

public enum InternalClaim {

    ROLES("roles");

    private final String claim;

    InternalClaim(String claim) {
        this.claim = claim;
    }

    public String getClaim() {
        return claim;
    }
}
