package net.dunice.newsFeed.service;

import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.response.BaseSuccessResponse;
import net.dunice.newsFeed.response.CustomSuccessResponse;

import java.util.UUID;

public interface UserService {
    CustomSuccessResponse getAllUsers();

    BaseSuccessResponse deleteUser();

    CustomSuccessResponse changeUser(PutUserDto putUserDto);

    CustomSuccessResponse getUserInfoById(UUID id);

    CustomSuccessResponse getUserInfo();

}
