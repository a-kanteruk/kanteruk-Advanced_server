package net.dunice.newsFeed.security;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.security.jwt.CustomUserDetails;
import net.dunice.newsFeed.security.jwt.JwtUserFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                                                            new CustomException(ValidationConstants.USER_NOT_FOUND));
        CustomUserDetails customUserDetails = JwtUserFactory.create(userEntity);
        return customUserDetails;
    }
}
