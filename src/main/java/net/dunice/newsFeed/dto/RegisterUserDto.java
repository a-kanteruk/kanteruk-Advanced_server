package net.dunice.newsFeed.dto;

import lombok.Data;
import net.dunice.newsFeed.models.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDto {
    String avatar;
    @NotBlank
    @Email
    String email;
    @NotBlank
    @Size(min = 3, max = 100)
    String name;
    @NotBlank
    @Size(min = 3, max = 25)
    String password;
    @Pattern(regexp = "base|user")
    String role;

}
