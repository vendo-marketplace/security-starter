package com.vendo.security_starter.jwt.parser;

import com.vendo.security_lib.type.TokenClaim;
import com.vendo.security_starter.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultClaimsParser implements TokenClaimsParser {

    private static final Logger log = LoggerFactory.getLogger(DefaultClaimsParser.class);

    @Override
    public TokenClaims extract(String token, String key) {
        try {
            Claims claims = JwtService.extractAll(token, key);
            Set<String> roles = extractRoles(claims, TokenClaim.ROLES.getClaim());
            return new TokenClaims(claims.getSubject(), roles, claims.getAudience());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IllegalStateException("Invalid token.");
        }
    }

    private Set<String> extractRoles(Claims claims, String rolesClaim) {
        Object rawRoles = claims.get(rolesClaim);

        if (rawRoles instanceof List<?> list) {
            if (list.isEmpty()) {
                return Set.of();
            } else if (list.stream().allMatch(String.class::isInstance)) {

                return list.stream()
                        .map(String.class::cast)
                        .collect(Collectors.toSet());
            }
        }

        throw new IllegalStateException("Invalid roles claim.");
    }
}
