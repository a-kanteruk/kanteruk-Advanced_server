package net.dunice.newsFeed.services.impl;

import java.util.Optional;
import java.util.UUID;

import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.LoginUserDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.models.Role;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;
import net.dunice.newsFeed.services.AuthService;
import net.dunice.newsFeed.services.AuthServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private AuthenticationManager authenticationManager;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(jwtTokenProvider,
                                            userRepository,
                                            bCryptPasswordEncoder,
                                            authenticationManager);
    }

    @Test
    void TestMethod_register_UserServiceTest() {
        given(jwtTokenProvider.createToken(getRegisterDto().getEmail())).willReturn("token");
        given(userRepository.save(any())).willReturn(getUserEntity());

        CustomSuccessResponse response = authService.register(getRegisterDto());
        LoginUserDto answer = (LoginUserDto) response.getData();

        verify(userRepository, times(1)).save(any());
        Assertions.assertNotNull(answer.getId());
        Assertions.assertNotNull(answer.getToken());
        Assertions.assertEquals(getTrueAnswer().getEmail(), answer.getEmail());
        Assertions.assertEquals(getTrueAnswer().getName(), answer.getName());
    }

    @Test
    void TestMethod_login_ReturnValueTest() {
        given(userRepository.findByEmail(any())).willReturn(Optional.ofNullable(getUserEntity()));
        given(jwtTokenProvider.createToken(any())).willReturn("token");

        CustomSuccessResponse response = authService.login(getAuthDto());
        LoginUserDto answer = (LoginUserDto) response.getData();

        verify(authenticationManager, times(1)).authenticate(any());
        Assertions.assertNotNull(answer.getId());
        Assertions.assertNotNull(answer.getToken());
        Assertions.assertEquals(getTrueAnswer().getEmail(), answer.getEmail());
        Assertions.assertEquals(getTrueAnswer().getAvatar(), answer.getAvatar());
    }

    private AuthDto getAuthDto() {
        return new AuthDto().setEmail(getTrueAnswer().getEmail())
                            .setPassword("123456");
    }

    private LoginUserDto getTrueAnswer() {
        return new LoginUserDto()
                .setAvatar("image")
                .setName("Garry")
                .setRole(Role.USER.getAuthority())
                .setEmail("mr.who@gmail.com");
    }
    private RegisterUserDto getRegisterDto() {
        return new RegisterUserDto()
                .setAvatar("image")
                .setName("Garry")
                .setEmail("mr.who@gmail.com")
                .setPassword("123456")
                .setRole(Role.USER.getAuthority());
    }

    private UserEntity getUserEntity() {
        UserEntity user = new UserEntity()
                .setId(UUID.randomUUID())
                .setAvatar("image")
                .setName("Garry")
                .setPassword("123456")
                .setEmail("mr.who@gmail.com")
                .setRole(Role.USER.getAuthority());
        return user;
    }
}
