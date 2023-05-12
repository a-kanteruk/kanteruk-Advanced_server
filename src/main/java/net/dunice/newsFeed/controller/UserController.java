package net.dunice.newsFeed.controller;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserInfoById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUserInfoById(id));
    }

    @GetMapping("/info")
    public ResponseEntity getUserInfo(){
        return ResponseEntity.ok(userService.getUserInfo());
    }

}
