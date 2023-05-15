package net.dunice.newsFeed.service;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.PublicUserView;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.mappers.UserMapper;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public CustomSuccessResponse getAllUsers() {
        return CustomSuccessResponse.getSuccessResponse(userRepository.findAllUsers());
    }

    @Override
    public CustomSuccessResponse getUserInfoById(UUID id) {
        PublicUserView user = UserMapper.INSTANCE
                                .UserEntityToPublicUserView(userRepository
                                        .findById(id)
                                        .orElseThrow(() -> new CustomException(ValidationConstants.USER_NOT_FOUND)));
        return CustomSuccessResponse.getSuccessResponse(user);
    }

    @Override
    public CustomSuccessResponse getUserInfo(UUID id) {
        PublicUserView user = UserMapper.INSTANCE
                                .UserEntityToPublicUserView(userRepository
                                        .findById(id)
                                        .orElseThrow(() -> new CustomException(ValidationConstants.USER_NOT_FOUND)));
        return CustomSuccessResponse.getSuccessResponse(user);
    }
}
