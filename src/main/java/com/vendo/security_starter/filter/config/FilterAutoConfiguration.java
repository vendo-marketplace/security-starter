package com.vendo.security_starter.filter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendo.security_starter.filter.exception.DefaultAccessDeniedHandler;
import com.vendo.security_starter.filter.exception.DefaultAuthenticationEntryPoint;
import com.vendo.security_starter.filter.header.DefaultHeaderExtractor;
import com.vendo.security_starter.filter.header.DefaultUserHeaderExtractor;
import com.vendo.security_starter.filter.header.HeaderExtractor;
import com.vendo.security_starter.filter.header.UserHeaderExtractor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class FilterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HeaderExtractor.class)
    public HeaderExtractor headerExtractor() {
        return new DefaultHeaderExtractor();
    }

    @Bean
    @ConditionalOnMissingBean(UserHeaderExtractor.class)
    public UserHeaderExtractor userHeaderExtractor() {
        return new DefaultUserHeaderExtractor();
    }

    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new DefaultAccessDeniedHandler(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new DefaultAuthenticationEntryPoint(objectMapper);
    }

}
