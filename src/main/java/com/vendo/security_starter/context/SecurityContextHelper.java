package com.vendo.security_starter.context;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper {

    public static <T> T getAuthFromContext(Class<T> tClass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("Unauthorized.");
        }

        Object principal = authentication.getPrincipal();

        if (!tClass.isInstance(principal)) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Principal is not of expected type %s.".formatted(tClass.getSimpleName())
            );
        }

        return tClass.cast(principal);
    }
}