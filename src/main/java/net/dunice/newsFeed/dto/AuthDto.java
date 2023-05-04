package net.dunice.newsFeed.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthDto {
    @Size(min = 3, max = 100)
    String email;
    String password;

}
