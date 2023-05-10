package net.dunice.newsFeed.service;


import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.LoginUserDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.models.Role;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInterface{

    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomSuccessResponse registry(RegisterUserDto registerUserDto){
        UserEntity newUser = userRepository.save(UserEntity.createUserEntity(registerUserDto));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getEmail(),
                                                                                    newUser.getPassword()));
        LoginUserDto loginUserDto = LoginUserDto.getLoginUserDto(newUser);
        loginUserDto.setToken(jwtTokenProvider.createToken(loginUserDto.getEmail(), Role.USER));
        return CustomSuccessResponse.getSuccessResponse(new LoginUserDto());
    }

}
