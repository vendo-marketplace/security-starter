package com.vendo.security_starter.jwt.parser;

public interface TokenClaimsParser {

    TokenClaims extract(String token, String key);

}
