package net.dunice.newsFeed.service;


import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.LoginUserDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.mappers.UserMapper;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public CustomSuccessResponse register(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new CustomException(ValidationConstants.USER_ALREADY_EXISTS);
        }
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        UserEntity newUser = userRepository.save(UserMapper.INSTANCE.registerDtoToUserEntity(registerUserDto));
        LoginUserDto response = UserMapper.INSTANCE.UserEntityToLoginUserDto(newUser);
        response.setToken(jwtTokenProvider.createToken(response.getEmail()));
        return CustomSuccessResponse.getSuccessResponse(response);
    }

    @Override
    public CustomSuccessResponse login(AuthDto authDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(),
                                                                                    authDto.getPassword()));
        LoginUserDto user = UserMapper.INSTANCE
                    .UserEntityToLoginUserDto(userRepository
                            .findByEmail(authDto.getEmail())
                            .orElseThrow(() -> new CustomException("User not exist.")));
        user.setToken(jwtTokenProvider.createToken(user.getEmail()));
        return CustomSuccessResponse.getSuccessResponse(user);
    }
}
