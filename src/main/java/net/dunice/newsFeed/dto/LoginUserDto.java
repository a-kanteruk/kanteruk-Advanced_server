package net.dunice.newsFeed.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.models.UserEntity;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class LoginUserDto {
    private String avatar;
    private String email;
    private UUID id;
    private String name;
    private String role;
    private String token;

    public static LoginUserDto getLoginUserDto(UserEntity userEntity){
        return new LoginUserDto().setAvatar(userEntity.getAvatar())
                                .setEmail(userEntity.getEmail())
                                .setId(userEntity.getId())
                                .setName(userEntity.getName())
                                .setRole(userEntity.getRole());
    }
}
