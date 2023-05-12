package net.dunice.newsFeed.service;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.response.BaseSuccessResponse;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public CustomSuccessResponse getAllUsers() {
        return null;
    }

    @Override
    public CustomSuccessResponse getUserInfoById(UUID id) {
        return null;
    }

    @Override
    public CustomSuccessResponse getUserInfo() {
        return null;
    }

    @Override
    public BaseSuccessResponse deleteUser() {
        return null;
    }

    @Override
    public CustomSuccessResponse changeUser(PutUserDto putUserDto) {
        return null;
    }
}
