package net.dunice.newsFeed.mappers;

import net.dunice.newsFeed.dto.LoginUserDto;
import net.dunice.newsFeed.dto.RegisterUserDto;
import net.dunice.newsFeed.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "avatar", source = "registerUserDto.avatar"),
            @Mapping(target = "email", source = "registerUserDto.email"),
            @Mapping(target = "name", source = "registerUserDto.name"),
            @Mapping(target = "password", source = "registerUserDto.password"),
            @Mapping(target = "role", source = "registerUserDto.role")
    })
    UserEntity registerDtoToUserEntity(RegisterUserDto registerUserDto);
    @Mappings({
            @Mapping(target = "id", source = "userEntity.id"),
            @Mapping(target = "avatar", source = "userEntity.avatar"),
            @Mapping(target = "email", source = "userEntity.email"),
            @Mapping(target = "name", source = "userEntity.name"),
            @Mapping(target = "role", source = "userEntity.role"),
            @Mapping(target = "token", ignore = true)
    })
    LoginUserDto UserEntityToLoginUserDto(UserEntity userEntity);
}