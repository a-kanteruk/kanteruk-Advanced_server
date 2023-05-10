package net.dunice.newsFeed.service;

import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.response.CustomSuccessResponse;

public interface AuthServiceInterface {
    CustomSuccessResponse registry(RegisterUserDto registerUserDto);
}
