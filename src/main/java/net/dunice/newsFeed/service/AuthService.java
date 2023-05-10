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
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CustomSuccessResponse registry(RegisterUserDto registerUserDto){
        UserEntity newUser = userRepository.save(UserEntity.createUserEntity(registerUserDto));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getEmail(),
                                                                                    newUser.getPassword()));
        LoginUserDto loginUserDto = LoginUserDto.getLoginUserDto(newUser);
        loginUserDto.setToken(jwtTokenProvider.createToken(loginUserDto.getEmail(), Role.USER));
        return CustomSuccessResponse.getSuccessResponse(new LoginUserDto());
    }


    @Override
    public CustomSuccessResponse login(AuthDto authDto) {
        String email = authDto.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, authDto.getPassword()));
        LoginUserDto result = LoginUserDto.getLoginUserDto(userRepository
                                                            .findByEmail(email)
                                                             .orElseThrow());

        result.setToken(jwtTokenProvider.createToken(email, Role.USER));
        return CustomSuccessResponse.getSuccessResponse(result);
    }
}
