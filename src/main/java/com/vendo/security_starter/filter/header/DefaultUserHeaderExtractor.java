package com.vendo.security_starter.filter.header;

import com.vendo.security_starter.type.UserHeader;
import com.vendo.user_lib.type.UserRole;
import com.vendo.user_lib.type.UserStatus;
import com.vendo.utils_lib.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vendo.core_lib.constants.Delimiters.COMMA_DELIMITER;

public class DefaultUserHeaderExtractor implements UserHeaderExtractor {

    private final HttpServletRequest request;

    public DefaultUserHeaderExtractor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public UserStatus extractStatus() {
        String status = request.getHeader(UserHeader.STATUS.getHeader());
        return UserStatus.valueOf(status);
    }

    @Override
    public Set<UserRole> extractRoles() {
        String roles = request.getHeader(UserHeader.ROLES.getHeader());

        if (StringUtils.isEmpty(roles)) {
            return Set.of();
        }

        return Arrays.stream(roles.split(COMMA_DELIMITER))
                .map(String::trim)
                .filter(role -> !role.isBlank())
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }

}
