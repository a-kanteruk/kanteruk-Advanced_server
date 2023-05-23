package net.dunice.newsFeed.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.constants.ValidationConstants;

@Data
@Accessors(chain = true)
public class AuthDto {
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
            "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;
    @NotBlank(message = ValidationConstants.PASSWORD_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.PASSWORD_NOT_VALID)
    private String password;
}
