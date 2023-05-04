package net.dunice.newsFeed.controller;

import net.dunice.newsFeed.dto.LoginUserDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class TestController {

    @PostMapping("/login")
    public ResponseEntity loginMethod(LoginUserDto loginUserDto){
        return ResponseEntity.ok(CustomSuccessResponse.getSuccessResponse());
    }

    @PostMapping("/register")
    public ResponseEntity registerMethod(@Valid RegisterUserDto registerUserDto){
        return ResponseEntity.ok(CustomSuccessResponse.getSuccessResponse());
    }
}
