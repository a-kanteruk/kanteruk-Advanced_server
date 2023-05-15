package net.dunice.newsFeed.service;

import java.util.UUID;

import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.response.BaseSuccessResponse;
import net.dunice.newsFeed.response.CustomSuccessResponse;

public interface UserService {
    CustomSuccessResponse getAllUsers();

    BaseSuccessResponse deleteUser(UUID id);

    CustomSuccessResponse changeUser(PutUserDto putUserDto);

    CustomSuccessResponse getUserInfoById(UUID id);

    CustomSuccessResponse getUserInfo(UUID id);

}
