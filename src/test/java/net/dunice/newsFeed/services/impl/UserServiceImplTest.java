package net.dunice.newsFeed.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.dunice.newsFeed.dto.PublicUserView;
import net.dunice.newsFeed.dto.PutUserDto;
import net.dunice.newsFeed.dto.PutUserDtoResponse;
import net.dunice.newsFeed.models.Role;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.services.UserService;
import net.dunice.newsFeed.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void TestMethod_GetAllUsers() {
        BDDMockito.given(userRepository.findAllUsers()).willReturn(List.of(getPublicUserView(), getPublicUserView()));
        CustomSuccessResponse response = userService.getAllUsers();
        List<PublicUserView> list = (List<PublicUserView>) response.getData();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void TestMethod_GetUserInfo() {
        BDDMockito.given(userRepository.findById(Mockito.any())).willReturn(Optional.ofNullable(getUserEntity()));
        CustomSuccessResponse response = userService.getUserInfo(UUID.randomUUID());
        PublicUserView data = (PublicUserView) response.getData();
        Assertions.assertEquals(getPublicUserView().getName(), data.getName());
        Assertions.assertEquals(getPublicUserView().getEmail(), data.getEmail());
        Assertions.assertEquals(getUserEntity().getAvatar(), data.getAvatar());
    }

    @Test
    void TestMethod_ChangeUser() {
        BDDMockito.given(userRepository.findById(Mockito.any())).willReturn(Optional.ofNullable(getUserEntity()));
        CustomSuccessResponse response = userService.changeUser(UUID.randomUUID(), getPutUserDto());
        PutUserDtoResponse data = (PutUserDtoResponse) response.getData();
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertNotNull(data.getId());
        Assertions.assertEquals(getPutUserDto().getName(), data.getName());
        Assertions.assertEquals(getPutUserDto().getEmail(), data.getEmail());
    }

    @Test
    void TestMethod_DeleteUser() {
        BDDMockito.given(userRepository.existsById(Mockito.any())).willReturn(true);
        userService.deleteUser(UUID.randomUUID());
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    private PublicUserView getPublicUserView() {
        return new PublicUserView().setId(UUID.randomUUID())
                                    .setAvatar("temp/5dffabed-2633-4130-9e6f-f221f682d13a.jpg")
                                    .setEmail("mr.who@gmail.com")
                                    .setName("Garry")
                                    .setRole(Role.USER.getAuthority());
    }

    private UserEntity getUserEntity() {
        return new UserEntity().setId(UUID.randomUUID())
                .setAvatar("temp/1bb9bfb1-49f2-4869-bb27-3f7ceeca9a45.jpeg")
                .setEmail("mr.who@gmail.com")
                .setName("Garry")
                .setPassword("123456")
                .setRole(Role.USER.getAuthority());
    }

    private PutUserDto getPutUserDto() {
        return new PutUserDto().setAvatar("temp/1bb9bfb1-49f2-4869-bb27-3f7ceeca9a45.jpeg")
                                .setEmail("mr.who@gmail.com")
                                .setName("Garry")
                                .setRole(Role.USER.getAuthority());
    }
}
