package net.dunice.newsFeed.controller;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.security.jwt.CustomUserDetails;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;
import net.dunice.newsFeed.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserInfoById(@PathVariable(required = true) @Size(min = 36, max = 36,
                                                  message = ValidationConstants.MAX_UPLOAD_SIZE_EXCEEDED) UUID id) {
        return ResponseEntity.ok(userService.getUserInfoById(id));
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserInfo(Authentication authentication){
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUserInfo(cud.getId()));
    }
}
