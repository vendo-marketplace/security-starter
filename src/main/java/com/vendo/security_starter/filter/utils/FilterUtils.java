package com.vendo.security_starter.filter.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Set;

public final class FilterUtils {
    private FilterUtils() {}

    public static void addAuthToContext(Object principal, Set<String> roles) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(principal, null, toAuthorities(roles));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    public static List<SimpleGrantedAuthority> toAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
