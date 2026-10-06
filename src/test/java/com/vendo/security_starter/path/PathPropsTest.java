package com.vendo.security_starter.path;

import com.vendo.security_starter.path.config.PathAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class PathPropsTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(PathAutoConfiguration.class))
            .withPropertyValues(
                    "endpoints.unauthenticated.general[0]=/actuator/health",
                    "endpoints.unauthenticated.general[1]=/swagger-ui/**",
                    "endpoints.unauthenticated.internal[0]=/internal/**",
                    "endpoints.unauthenticated.product[0]=/categories/tree"
            );

    @Test
    void allPaths_shouldReturnGeneralPaths_whenNoGroupsPermitted() {
        contextRunner.run(context -> assertThat(context.getBean(PathProps.class).allPaths())
                .containsExactlyInAnyOrder("/actuator/health", "/swagger-ui/**"));
    }

    @Test
    void allPaths_shouldReturnPathsOfPermittedGroups() {
        contextRunner
                .withPropertyValues("endpoints.permitted-groups=general,internal")
                .run(context -> assertThat(context.getBean(PathProps.class).allPaths())
                        .containsExactlyInAnyOrder("/actuator/health", "/swagger-ui/**", "/internal/**"));
    }

    @Test
    void allPaths_shouldSkipPermittedGroup_whenItHasNoConfiguredPaths() {
        contextRunner
                .withPropertyValues("endpoints.permitted-groups=general,auth")
                .run(context -> assertThat(context.getBean(PathProps.class).allPaths())
                        .containsExactlyInAnyOrder("/actuator/health", "/swagger-ui/**"));
    }

    @Test
    void paths_shouldReturnOnlyRequestedGroups() {
        contextRunner
                .withPropertyValues("endpoints.permitted-groups=general,internal,product")
                .run(context -> assertThat(context.getBean(PathProps.class).paths(PathGroup.PRODUCT))
                        .containsExactly("/categories/tree"));
    }

    @Test
    void allPaths_shouldReturnEmpty_whenNothingConfigured() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(PathAutoConfiguration.class))
                .run(context -> assertThat(context.getBean(PathProps.class).allPaths()).isEmpty());
    }

}
