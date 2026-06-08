package com.vendo.security_starter.filter.config;

import com.vendo.security_starter.filter.header.DefaultHeaderExtractor;
import com.vendo.security_starter.filter.header.DefaultUserHeaderExtractor;
import com.vendo.security_starter.filter.header.HeaderExtractor;
import com.vendo.security_starter.filter.header.UserHeaderExtractor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
