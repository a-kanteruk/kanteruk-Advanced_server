package net.dunice.newsFeed.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import net.dunice.newsFeed.constants.ValidationConstants;

@Data
public class RegisterUserDto {
    @NotBlank(message = ValidationConstants.USER_AVATAR_NOT_NULL)
    private String avatar;
    @NotNull(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                     "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
                        message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    private String email;
    @NotBlank(message = ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT)
    @Size(min = 3, max = 25, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    private String name;
    @NotBlank(message = ValidationConstants.PASSWORD_NOT_NULL)
    @Size(min = 3, max = 100, message = ValidationConstants.PASSWORD_NOT_VALID)
    private String password;
    @NotBlank(message = ValidationConstants.USER_ROLE_NOT_NULL)
    @Pattern(regexp = "base|user", message = ValidationConstants.UNKNOWN)
    private String role;

}
