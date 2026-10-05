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

    private Map<PathGroup, Set<String>> unauthenticated = new EnumMap<>(PathGroup.class);
    private Set<PathGroup> enabledGroups = EnumSet.of(PathGroup.GENERAL);

    public String[] allPaths() {
        return paths(enabledGroups);
    }

    public String[] paths(PathGroup... groups) {
        return paths(Arrays.asList(groups));
    }

    private String[] paths(Collection<PathGroup> groups) {
        return groups.stream()
                .map(unauthenticated::get)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .distinct()
                .toArray(String[]::new);
    }

    public Map<PathGroup, Set<String>> getUnauthenticated() {
        return unauthenticated;
    }

    public void setUnauthenticated(Map<PathGroup, Set<String>> unauthenticated) {
        this.unauthenticated = unauthenticated;
    }

    public Set<PathGroup> getEnabledGroups() {
        return enabledGroups;
    }

    public void setEnabledGroups(Set<PathGroup> enabledGroups) {
        this.enabledGroups = enabledGroups;
    }

}
