package net.dunice.newsFeed.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDto {
    String avatar;
    @Email
    String email;
    @Size(min = 3, max = 100)
    String name;
    @Size(min = 3, max = 25)
    String password;
    String role = "USER";

}
