package com.vendo.security_starter.jwt;

import java.util.Map;

public record JwtPayload(
        String subject,
        Map<String, Object> claims,
        long expiration) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String subject;
        private Map<String, Object> claims;
        private long expiration;

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder claims(Map<String, Object> claims) {
            this.claims = claims;
            return this;
        }

        public Builder expiration(long expiration) {
            this.expiration = expiration;
            return this;
        }

        public JwtPayload build() {
            return new JwtPayload(subject, claims, expiration);
        }
    }

}