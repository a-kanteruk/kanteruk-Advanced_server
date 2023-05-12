package net.dunice.newsFeed.models;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("user");
    private final String roleName;
    @Override
    public String getAuthority() {
        return roleName;
    }
}
