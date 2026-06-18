package com.vendo.security_starter.jwt;

import com.vendo.core_lib.utils.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.CollectionUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

public class JwtService {

    public static long SKEW_SECONDS = 60;

    public static Claims extractAll(String token, String secret) {
        return parseSignedClaims(token, secret).getPayload();
    }

    public static String buildToken(JwtPayload payload, String secret) {
        if (payload == null || StringUtils.isEmpty(payload.subject())) {
            throw new IllegalArgumentException("Invalid payload.");
        }

        JwtBuilder jwtBuilder = Jwts.builder();
        if (!CollectionUtils.isEmpty(payload.audience())) {
            payload.audience().forEach(aud -> jwtBuilder.audience().add(aud));
        }

        Instant now = Instant.now();
        return jwtBuilder
                .claims(payload.claims())
                .subject(payload.subject())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(payload.expiration())))
                .signWith(getSignInKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Jws<Claims> parseSignedClaims(String token, String secret) throws JwtException {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignInKey(secret))
                .clockSkewSeconds(SKEW_SECONDS)
                .build()
                .parseSignedClaims(token);
    }

    private static Key getSignInKey(String secret) {
        try {
            return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid token.");
        }
    }

    public static void setSkew(long seconds) {
        if (seconds < 0) throw new IllegalArgumentException("Skew cannot be lower than 0.");
        SKEW_SECONDS = seconds;
    }
}
