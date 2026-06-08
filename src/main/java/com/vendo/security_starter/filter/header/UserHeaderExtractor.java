package com.vendo.security_starter.filter.header;

import com.vendo.user_lib.type.UserRole;
import com.vendo.user_lib.type.UserStatus;

import java.util.Set;

public interface UserHeaderExtractor {

    UserStatus extractStatus() throws IllegalArgumentException;
    Set<UserRole> extractRoles();

}
