package net.dunice.newsFeed.security.jwt;

import lombok.NoArgsConstructor;
import net.dunice.newsFeed.models.Role;
import net.dunice.newsFeed.models.UserEntity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

@NoArgsConstructor
public class JwtUserFactory {

    public static CustomUserDetails create(UserEntity userEntity) {
        return new CustomUserDetails(userEntity.getId(),
                                         userEntity.getAvatar(),
                                         userEntity.getEmail(),
                                         userEntity.getName(),
                                         userEntity.getPassword(),
                                         getAuthorities());
    }

    private static SimpleGrantedAuthority getAuthorities() {
        return new SimpleGrantedAuthority(Role.USER.getAuthority());
    }
}
