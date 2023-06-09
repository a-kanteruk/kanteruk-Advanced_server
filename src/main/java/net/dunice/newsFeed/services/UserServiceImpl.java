package net.dunice.newsFeed.services;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.PublicUserView;
import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.mappers.UserMapper;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.BaseSuccessResponse;
import net.dunice.newsFeed.responses.CustomSuccessResponse;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public CustomSuccessResponse getAllUsers() {
        return CustomSuccessResponse.getSuccessResponse(userRepository.findAllUsers());
    }

    @Override
    public CustomSuccessResponse getUserInfo(UUID id) {
        PublicUserView user = UserMapper.INSTANCE
                                .UserEntityToPublicUserView(userRepository
                                        .findById(id)
                                        .orElseThrow(() -> new CustomException(ValidationConstants.USER_NOT_FOUND)));
        return CustomSuccessResponse.getSuccessResponse(user);
    }

    @Override
    public CustomSuccessResponse changeUser(UUID id, PutUserDto putUserDto) {
        UserEntity changeUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ValidationConstants.USER_NOT_FOUND))
                .setAvatar("" + FilesServiceImpl.uploading)
                .setEmail(putUserDto.getEmail())
                .setName(putUserDto.getName()).setRole(putUserDto.getRole());
        userRepository.save(changeUser);
        return CustomSuccessResponse.getSuccessResponse(UserMapper.INSTANCE.UserEntityToPutUserDtoResponse(changeUser));
    }

    @Override
    public BaseSuccessResponse deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(ValidationConstants.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
        return BaseSuccessResponse.getSuccessResponse();
    }
}
