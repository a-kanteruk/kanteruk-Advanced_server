package net.dunice.newsFeed.service;

import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.response.CustomSuccessResponse;

public interface AuthService {
    CustomSuccessResponse register(RegisterUserDto registerUserDto);
}
