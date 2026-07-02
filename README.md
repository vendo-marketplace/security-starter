# security-starter

## Overview
`security-starter` is a Spring Boot autoconfiguration starter for the Vendo ecosystem that provides ready-to-use JWT authentication and authorization building blocks for microservices. It wires up filters, JWT parsing, header extraction, and security context handling out of the box via Spring Boot auto-configuration, so consuming services only need to add the dependency and configure properties.

## Features

* **JWT handling** — `JwtService` and `DefaultClaimsParser`/`TokenClaimsParser` for parsing and validating JWT tokens, with `JwtPayload` and `TokenClaims` representing parsed token data
* **Security filter chain** — `FilterAutoConfiguration` wires a request filter that extracts and validates authentication data automatically
* **Header extraction** — `HeaderExtractor`/`UserHeaderExtractor` interfaces with default implementations (`DefaultHeaderExtractor`, `DefaultUserHeaderExtractor`) for pulling auth-related data (e.g. user id, roles) from request headers
* **Security context propagation** — `SecurityContextHelper` for convenient access to the authenticated user's data within the application layer
* **Centralized exception handling** — `DefaultAccessDeniedHandler` and `DefaultAuthenticationEntryPoint` for consistent 401/403 responses across services
* Auto-configuration based — activates automatically once added as a dependency, following Spring Boot starter conventions

## Installation
Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.vendo-marketplace-be</groupId>
    <artifactId>security-starter</artifactId>
    <version>latest-version</version>
    <packaging>jar</packaging>
</dependency>
```

The starter relies on `spring-boot-starter-security` and `spring-boot-starter-web` (both `provided` scope), so the consuming service must already include them, along with the Vendo `user-lib`, `core-lib`, and `security-lib` dependencies.

## Usage
Once added, `security-starter` auto-configures the JWT filter chain, header extraction, and exception handlers via `JwtAutoConfiguration` and `FilterAutoConfiguration`. No manual bean wiring is required for the default setup.

Example — accessing the current authenticated user via `SecurityContextHelper`:

```java
import com.vendo.security_starter.context.SecurityContextHelper;

// Example usage
String currentUserId = SecurityContextHelper.getCurrentUserId();
```

Example — customizing header extraction by providing your own `UserHeaderExtractor` bean, which overrides the default:

```java
import com.vendo.security_starter.filter.header.UserHeaderExtractor;

@Bean
public UserHeaderExtractor customUserHeaderExtractor() {
    return request -> request.getHeader("X-Custom-User-Id");
}
```

Configuration (e.g. JWT secret, issuer, header names) is provided through `application.yaml` properties, picked up automatically by `JwtAutoConfiguration` and `FilterAutoConfiguration`.

## Requirements

* Java 17
* Spring Boot 3.5.x
* `spring-boot-starter-security` and `spring-boot-starter-web` on the classpath
* Vendo `user-lib`, `core-lib`, and `security-lib` dependencies

## Notes
This starter is intended solely for internal use within the Vendo ecosystem (`io.github.vendo-marketplace-be`). It builds on top of `security-lib` (shared security DTOs/enums/exceptions) but focuses specifically on Spring auto-configuration and runtime wiring — actual security contracts (headers, claims, exception response format) still live in `security-lib`. Default handlers/extractors can be overridden by defining custom beans, which take precedence over the auto-configured defaults.