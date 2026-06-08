package com.vendo.security_starter.jwt.parser;

import java.util.Set;

public record TokenClaims(
        String subject,
        Set<String> roles,
        Set<String> audience
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String subject;
        private Set<String> roles;
        private Set<String> audience;

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder audience(Set<String> audience) {
            this.audience = audience;
            return this;
        }

        public TokenClaims build() {
            return new TokenClaims(subject, roles, audience);
        }
    }
}
