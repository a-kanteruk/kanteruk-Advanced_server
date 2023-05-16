package net.dunice.newsFeed.controller;

import java.util.UUID;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.security.jwt.CustomUserDetails;
import net.dunice.newsFeed.services.UserServiceImpl;
import org.hibernate.validator.constraints.Length;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/{stringId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserInfoById(@PathVariable @Length(min = 36, max = 36,
                                            message = ValidationConstants.MAX_UPLOAD_SIZE_EXCEEDED) String stringId) {
        return ResponseEntity.ok(userService.getUserInfoById(UUID.fromString(stringId)));
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserInfo(Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUserInfo(cud.getId()));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeUserInfo(@RequestBody @Valid PutUserDto putUserDto, Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.changeUser(cud.getId(), putUserDto));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.deleteUser(cud.getId()));
    }
}
