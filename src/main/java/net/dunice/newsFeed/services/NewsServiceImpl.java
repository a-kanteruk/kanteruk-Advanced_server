package net.dunice.newsFeed.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.GetNewsOutDto;
import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.mappers.NewsMapper;
import net.dunice.newsFeed.models.NewsEntity;
import net.dunice.newsFeed.models.TagEntity;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.NewsRepository;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.CreateNewsSuccessResponse;

import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.responses.PageableResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        NewsEntity newsEntity = NewsMapper.INSTANCE.NewsDtoToNewsEntity(newsDto).setUser(userEntity);
        newsRepository.save(newsEntity);
        List<TagEntity> tagList = newsDto.getTags().stream().map(tag -> new TagEntity().setTag(tag).setNews(newsEntity))
                                                                                        .collect(Collectors.toList());
        newsEntity.setTags(tagList);
        newsRepository.save(newsEntity);
        return CreateNewsSuccessResponse.CreateNewsResponse(newsEntity.getId());
    }

    @Override
    public CustomSuccessResponse<PageableResponse> getPaginatedNews(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        List<NewsEntity> listNewsEntity = newsRepository.findAll(pageable).getContent();
        List<GetNewsOutDto> listNewsDto = listNewsEntity.stream()
                .map(newsEntity -> NewsMapper.INSTANCE.NewsEntityToGetNewsOutDto(newsEntity)
                                .setUserId(newsEntity.getUser().getId())
                                .setUsername(newsEntity.getUser().getName())
                                .setTags(newsEntity.getTags().stream()
                                                     .map(tag -> NewsMapper.INSTANCE.TagEntityToTag(tag))
                                                     .collect(Collectors.toList()))).collect(Collectors.toList());
        Long numberOfElements = listNewsDto.stream().count();
        return CustomSuccessResponse.getSuccessResponse(PageableResponse.createPageableResponse(listNewsDto,
                                                                                                numberOfElements));
    }
}
