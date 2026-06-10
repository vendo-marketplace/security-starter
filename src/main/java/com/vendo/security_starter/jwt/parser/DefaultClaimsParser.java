package com.vendo.security_starter.jwt.parser;

import com.vendo.core_lib.utils.StringUtils;
import com.vendo.security_lib.type.TokenClaim;
import com.vendo.security_starter.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vendo.core_lib.constants.Delimiters.COMMA_DELIMITER;

public class DefaultClaimsParser implements TokenClaimsParser {

    private static final Logger log = LoggerFactory.getLogger(DefaultClaimsParser.class);

    @Override
    public TokenClaims extract(String token, String key) {
        try {
            Claims claims = JwtService.extractAll(token, key);
            Set<String> roles = extractRoles(claims);
            return new TokenClaims(claims.getSubject(), roles, claims.getAudience());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IllegalStateException("Invalid token.");
        }
    }

    private Set<String> extractRoles(Claims claims) {
        String roles = claims.get(TokenClaim.ROLES.getClaim(), String.class);

        if (StringUtils.isEmpty(roles)) {
            return Set.of();
        }

        return Arrays.stream(roles.split(COMMA_DELIMITER)).collect(Collectors.toSet());
    }
}
