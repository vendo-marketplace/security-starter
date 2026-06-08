package com.vendo.security_starter.filter.header;

import com.vendo.user_lib.type.UserRole;
import com.vendo.user_lib.type.UserStatus;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

public interface UserHeaderExtractor {

    UserStatus extractStatus(HttpServletRequest request) throws IllegalArgumentException;
    Set<UserRole> extractRoles(HttpServletRequest request);

}
