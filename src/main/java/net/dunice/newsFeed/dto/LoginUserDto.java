package net.dunice.newsFeed.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class LoginUserDto {
    String avatar;
    String email;
    String id;
    String name;
    String role = "USER";
    String token;
}
