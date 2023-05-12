package net.dunice.newsFeed.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(authService.register(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }
}
