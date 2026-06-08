package com.vendo.security_starter.filter.utils;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Set;

public final class FilterUtils {
    private FilterUtils() {}

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static void addAuthToContext(Object principal, Set<String> roles) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(principal, null, toAuthorities(roles));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    public static String getTokenFromRequest(String authorization) {
        if (authorization == null) {
            throw new AuthenticationCredentialsNotFoundException("Unauthorized.");
        } else if (!authorization.startsWith(BEARER_PREFIX)) {
            throw new BadCredentialsException("Invalid token.");
        }

        return authorization.substring(BEARER_PREFIX.length());
    }

    public static List<SimpleGrantedAuthority> toAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
