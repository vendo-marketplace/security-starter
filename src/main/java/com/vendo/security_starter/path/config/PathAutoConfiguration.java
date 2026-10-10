package com.vendo.security_starter.path.config;

import com.vendo.security_starter.path.PathProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PathProps.class)
public class PathAutoConfiguration {
}
