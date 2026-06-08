package com.vendo.security_starter.filter.header;

import com.vendo.core_lib.utils.StringUtils;
import com.vendo.security_lib.type.UserHeader;
import com.vendo.user_lib.type.UserRole;
import com.vendo.user_lib.type.UserStatus;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vendo.core_lib.constants.Delimiters.COMMA_DELIMITER;

public class DefaultUserHeaderExtractor implements UserHeaderExtractor {

    @Override
    public UserStatus extractStatus(HttpServletRequest request) {
        String status = request.getHeader(UserHeader.STATUS.getHeader());
        return UserStatus.valueOf(status);
    }

    @Override
    public Set<UserRole> extractRoles(HttpServletRequest request) {
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
