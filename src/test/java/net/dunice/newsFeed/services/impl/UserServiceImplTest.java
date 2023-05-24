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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
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
        given(userRepository.findAllUsers()).willReturn(List.of(getPublicUserView(), getPublicUserView()));

        CustomSuccessResponse response = userService.getAllUsers();
        List<PublicUserView> list = (List<PublicUserView>) response.getData();

        assertEquals(2, list.size());
    }

    @Test
    void TestMethod_GetUserInfo() {
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(getUserEntity()));

        CustomSuccessResponse response = userService.getUserInfo(UUID.randomUUID());
        PublicUserView data = (PublicUserView) response.getData();

        assertEquals(getPublicUserView().getName(), data.getName());
        assertEquals(getPublicUserView().getEmail(), data.getEmail());
        assertEquals(getUserEntity().getAvatar(), data.getAvatar());
    }

    @Test
    void TestMethod_ChangeUser() {
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(getUserEntity()));

        CustomSuccessResponse response = userService.changeUser(UUID.randomUUID(), getPutUserDto());
        PutUserDtoResponse data = (PutUserDtoResponse) response.getData();

        Mockito.verify(userRepository, times(1)).save(any());
        assertNotNull(data.getId());
        assertEquals(getPutUserDto().getName(), data.getName());
        assertEquals(getPutUserDto().getEmail(), data.getEmail());
    }

    @Test
    void TestMethod_DeleteUser() {
        given(userRepository.existsById(any())).willReturn(true);

        userService.deleteUser(UUID.randomUUID());

        Mockito.verify(userRepository, times(1)).deleteById(any());
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
