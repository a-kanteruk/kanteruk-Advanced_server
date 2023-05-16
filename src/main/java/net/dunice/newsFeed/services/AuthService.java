package net.dunice.newsFeed.services;

import net.dunice.newsFeed.dto.AuthDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.responses.CustomSuccessResponse;

public interface AuthService {
    CustomSuccessResponse register(RegisterUserDto registerUserDto);

    CustomSuccessResponse login(AuthDto authDto);
}
