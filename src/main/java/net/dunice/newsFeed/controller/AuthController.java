package net.dunice.newsFeed.controller;


import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity registerMethod(@Valid RegisterUserDto registerUserDto){
        return ResponseEntity.ok(authService.registry(registerUserDto));
    }
}
