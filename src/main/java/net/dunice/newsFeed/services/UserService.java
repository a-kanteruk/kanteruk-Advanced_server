package net.dunice.newsFeed.services;

import java.util.UUID;

import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.responses.BaseSuccessResponse;
import net.dunice.newsFeed.responses.CustomSuccessResponse;

public interface UserService {
    CustomSuccessResponse getAllUsers();

    CustomSuccessResponse getUserInfo(UUID id);

    CustomSuccessResponse changeUser(UUID id, PutUserDto putUserDto);

    BaseSuccessResponse deleteUser(UUID id);

}
