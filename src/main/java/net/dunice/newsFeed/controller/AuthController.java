package net.dunice.newsFeed.controller;

import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.LoginUserDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.models.User;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import net.dunice.newsFeed.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;

        @Autowired
        PasswordEncoder encoder;

        @Autowired
        JwtUtils jwtUtils;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateuser(@RequestBody AuthDto authDto) {
            org.springframework.security.core.Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
            SecurityContextHolder.getContext()
                                .setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl)
                    authentication.getPrincipal();

            return ResponseEntity
                    .ok(new JwtResponse(jwt, userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail()));
        }

        @PostMapping("/register")
        public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
            if (userRepository.existsByUsername(registerUserDto.getName())) {
                return ResponseEntity.badRequest()
                                    .body(new MessageResponse("Error: username is already taken!"));
            }

            if (userRepository.existsByEmail(registerUserDto.getEmail())) {
                return ResponseEntity.badRequest().body(new CustomSuccessResponse.getBadResponse("Error"));
            }
            // Create new user account
            User user = new User(registerUserDto.getName(),
                                 registerUserDto.getEmail(),
                                 encoder.encode(registerUserDto.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok(new CustomSuccessResponse());
        }
}
