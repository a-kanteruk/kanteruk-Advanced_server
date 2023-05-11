package net.dunice.newsFeed.dto;

import lombok.Data;
import net.dunice.newsFeed.constants.ValidationConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDto {
    @NotBlank(message = ValidationConstants.USER_AVATAR_NOT_NULL)
    String avatar;
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = ValidationConstants.USER_EMAIL_NOT_VALID)
    String email;
    @NotBlank(message = ValidationConstants.USERNAME_HAS_TO_BE_PRESENT)
    @Size(min = 3, max = 100, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    String name;
    @NotBlank(message = ValidationConstants.PASSWORD_NOT_NULL)
    @Size(min = 3, max = 25, message = ValidationConstants.PASSWORD_NOT_VALID)
    String password;
    @NotBlank(message = ValidationConstants.USER_ROLE_NOT_NULL)
    @Pattern(regexp = "base|user", message = ValidationConstants.USER_ROLE_NOT_VALID)
    String role;

}
