package net.dunice.newsFeed.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import net.dunice.newsFeed.constants.ValidationConstants;


@Data
public class AuthDto {
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    String email;
    @NotBlank(message = ValidationConstants.PASSWORD_NOT_NULL)
    String password;

}
