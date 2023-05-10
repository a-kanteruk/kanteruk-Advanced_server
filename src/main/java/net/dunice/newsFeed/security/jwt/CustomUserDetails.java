package net.dunice.newsFeed.security.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
public class CustomUserDetails implements UserDetails {
    private final UUID id;
    private final String avatar;
    private final String email;
    private final String name;
    private final String password;
    private final String role;

    public CustomUserDetails(UUID id, String avatar, String email, String name, String password, SimpleGrantedAuthority role) {
        this.id = id;
        this.avatar = avatar;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = String.valueOf(role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
