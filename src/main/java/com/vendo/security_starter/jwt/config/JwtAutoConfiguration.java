package com.vendo.security_starter.jwt.config;

import com.vendo.security_starter.jwt.parser.DefaultClaimsParser;
import com.vendo.security_starter.jwt.parser.TokenClaimsParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TokenClaimsParser tokenClaimsParser() {
        return new DefaultClaimsParser();
    }

}
