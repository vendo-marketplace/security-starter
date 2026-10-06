package com.vendo.security_starter.path;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@ConfigurationProperties(prefix = "endpoints")
public class PathProps {

    /**
     * All unauthenticated path groups. This config is shared by every service, so it holds the groups of all of them.
     */
    private Map<PathGroup, Set<String>> unauthenticated = new EnumMap<>(PathGroup.class);

    /**
     * Groups from {@link #unauthenticated} that this service lets through without a token.
     */
    private Set<PathGroup> permittedGroups = EnumSet.of(PathGroup.GENERAL);

    public String[] allPaths() {
        return paths(permittedGroups);
    }

    public String[] paths(PathGroup... groups) {
        return paths(Arrays.asList(groups));
    }

    private String[] paths(Collection<PathGroup> groups) {
        return groups.stream()
                .map(unauthenticated::get)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toArray(String[]::new);
    }

    public Map<PathGroup, Set<String>> getUnauthenticated() {
        return unauthenticated;
    }

    public void setUnauthenticated(Map<PathGroup, Set<String>> unauthenticated) {
        this.unauthenticated = unauthenticated;
    }

    public Set<PathGroup> getPermittedGroups() {
        return permittedGroups;
    }

    public void setPermittedGroups(Set<PathGroup> permittedGroups) {
        this.permittedGroups = permittedGroups;
    }

}
