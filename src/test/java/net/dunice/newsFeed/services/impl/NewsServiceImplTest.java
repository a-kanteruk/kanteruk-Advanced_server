package net.dunice.newsFeed.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.models.NewsEntity;
import net.dunice.newsFeed.models.Role;
import net.dunice.newsFeed.models.TagEntity;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.NewsRepository;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.BaseSuccessResponse;
import net.dunice.newsFeed.responses.CreateNewsSuccessResponse;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.responses.PageableResponse;
import net.dunice.newsFeed.services.FilesService;
import net.dunice.newsFeed.services.FilesServiceImpl;
import net.dunice.newsFeed.services.NewsService;
import net.dunice.newsFeed.services.NewsServiceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NewsServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private NewsRepository newsRepository;
    private NewsService newsService;
    private FilesService filesService;
    private Page<NewsEntity> newsList;

    @BeforeEach
    void setUp() {
        newsService = new NewsServiceImpl(userRepository, newsRepository);
        filesService = new FilesServiceImpl();
        newsList = new PageImpl<>(List.of(getNewsEntity(), getNewsEntity()));
    }

    @Test
    void TestMethod_CreateNews() {
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(getUserEntity()));
        given(newsRepository.save(any())).willReturn(new NewsEntity());

        CreateNewsSuccessResponse response = newsService.createNews(getNewsDto(), getUserEntity().getId());

        verify(newsRepository, times(2)).save(any());
        assertNotNull(response);
        System.out.println(response.getId());
    }

    @Test
    void TestMethod_GetPaginatedNews() {
        given(newsRepository.findAll(any(Pageable.class))).willReturn(newsList);
        given(newsRepository.count()).willReturn(2L);

        CustomSuccessResponse response = newsService.getPaginatedNews(2, 2);
        PageableResponse data = (PageableResponse) response.getData();

        assertEquals(2L, data.getNumberOfElements());
        assertNotNull(data.getContent());
    }

    @Test
    void TestMethod_GetPaginatedUserNews() {
        given(newsRepository.findAllByUserId(any(), any(Pageable.class))).willReturn(newsList);

        CustomSuccessResponse response = newsService.getPaginatedUserNews(2, 2, UUID.randomUUID());
        PageableResponse data = (PageableResponse) response.getData();

        assertEquals(2L, data.getNumberOfElements());
        assertNotNull(data.getContent().get(0).getUserId());
    }

    @Test
    void TestMethod_GetFindNews() {
        newsList.and(getNewsEntity().setDescription("Other"));
        given(newsRepository.findAll(any(Pageable.class))).willReturn(newsList);

        PageableResponse response = newsService.getFindNews(2, 2, null, "Desc", null);

        assertEquals(2, response.getContent().size());
        assertEquals("Desc", response.getContent().get(0).getDescription());
    }

    @Test
    void TestMethod_ChangeNews() throws IOException {
        given(newsRepository.findById(any())).willReturn(Optional.ofNullable(getNewsEntity()));
        given(newsRepository.save(any())).willReturn(getNewsEntity());

        filesService.uploadFile(getNewFile());
        BaseSuccessResponse response = newsService.changeNews(1L, getNewsDto());

        assertNotNull(response);
    }

    @Test
    void TestMethod_DeleteNews() {
        given(newsRepository.existsById(any())).willReturn(true);

        BaseSuccessResponse response = newsService.deleteNews(1L);

        assertEquals(1, response.getStatusCode());
        verify(newsRepository, times(1)).deleteById(any());
    }

    private NewsDto getNewsDto() {
        return new NewsDto().setDescription("Some news")
                            .setImage("temp/96e49d1a-a284-4156-b0b8-92cd2afb099a.jpg")
                            .setTitle("News")
                            .setTags(List.of("Test"));
    }

    private UserEntity getUserEntity() {
        return new UserEntity().setId(UUID.randomUUID())
                                .setAvatar("temp/1bb9bfb1-49f2-4869-bb27-3f7ceeca9a45.jpeg")
                                .setEmail("mr.who@gmail.com")
                                .setName("Garry")
                                .setPassword("123456")
                                .setRole(Role.USER.getAuthority());
    }

    private NewsEntity getNewsEntity() {
        return new NewsEntity().setId(1L)
                                .setDescription("Desc")
                                .setImage("temp/1bb9bfb1-49f2-4869-bb27-3f7ceeca9a45.jpeg")
                                .setTitle("This")
                                .setUser(getUserEntity())
                                .setTags(List.of(new TagEntity()
                                                        .setId(1L)
                                                        .setTag("Test")));
    }

    private MultipartFile getNewFile() throws IOException {
        File file = new File("temp/7db6fca7-e13f-4ee4-9a93-c724377eca80.jpeg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                                                                                            IOUtils.toByteArray(input));
        return multipartFile;
    }
}
