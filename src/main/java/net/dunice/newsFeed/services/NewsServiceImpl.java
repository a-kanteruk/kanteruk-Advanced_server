package net.dunice.newsFeed.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.mappers.NewsMapper;
import net.dunice.newsFeed.models.NewsEntity;
import net.dunice.newsFeed.models.TagEntity;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.NewsRepository;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.CreateNewsSuccessResponse;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    @Override
    public CreateNewsSuccessResponse createNews(NewsDto newsDto, UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomException(ValidationConstants.USER_NOT_FOUND));
        List<TagEntity> list = newsDto.getTags().stream().map(tag -> new TagEntity().setTag(tag))
                                                         .collect(Collectors.toList());
        NewsEntity newsEntity = NewsMapper.INSTANCE.NewsDtoToNewsEntity(newsDto);
        newsEntity.setTags(list).setUser(userEntity);
        newsRepository.save(newsEntity);
        return CreateNewsSuccessResponse.CreateNewsResponse(newsEntity.getId());
    }
}
