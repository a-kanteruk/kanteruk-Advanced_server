package net.dunice.newsFeed.services.impl;

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
import net.dunice.newsFeed.responses.CreateNewsSuccessResponse;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.responses.PageableResponse;
import net.dunice.newsFeed.services.NewsService;
import net.dunice.newsFeed.services.NewsServiceImpl;

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
public class NewsServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private NewsRepository newsRepository;
    private NewsService newsService;

    @BeforeEach
    void setUp() {
        newsService = new NewsServiceImpl(userRepository, newsRepository);
    }

    @Test
    void TestMethod_createNews() {
        BDDMockito.given(userRepository.findById(Mockito.any())).willReturn(Optional.ofNullable(getUserEntity()));
        BDDMockito.given(newsRepository.save(Mockito.any())).willReturn(new NewsEntity());

        CreateNewsSuccessResponse response = newsService.createNews(getNewsDto(), getUserEntity().getId());

        Mockito.verify(newsRepository, Mockito.times(2)).save(Mockito.any());
        Assertions.assertNotNull(response);
        System.out.println(response.getId());
    }

    @Test
    void TestMethod_getPaginatedNews() {
        BDDMockito.given(newsRepository.findAll()).willReturn(List.of(getNewsEntity(),getNewsEntity()));
        BDDMockito.given(newsRepository.count()).willReturn(2L);

        CustomSuccessResponse response = newsService.getPaginatedNews(2, 2);
        PageableResponse answer = (PageableResponse) response.getData();

        Assertions.assertEquals(2L, answer.getNumberOfElements());
        Assertions.assertNotNull(answer.getContent());

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
                                .setDescription("Description")
                                .setImage("temp/1bb9bfb1-49f2-4869-bb27-3f7ceeca9a45.jpeg")
                                .setTitle("This")
                                .setUser(getUserEntity())
                                .setUsername(null)
                                .setTags(List.of(new TagEntity()
                                                        .setId(1L)
                                                        .setTag("Test")
                                                        .setNews(null)));
    }
}
