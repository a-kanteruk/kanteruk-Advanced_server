package net.dunice.newsFeed.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.dto.RegisterUserDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String avatar;
    @Column(unique = true)
    @NotBlank
    private String email;
    @Column(unique = true)
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String role;

    public static UserEntity createUserEntity(RegisterUserDto registerUserDto){
        return new UserEntity().setAvatar(registerUserDto.getAvatar())
                .setEmail(registerUserDto.getEmail())
                .setName(registerUserDto.getName())
                .setPassword(registerUserDto.getPassword())
                .setRole(registerUserDto.getRole());
    }
}
