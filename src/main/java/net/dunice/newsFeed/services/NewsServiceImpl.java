package net.dunice.newsFeed.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.GetNewsOutDto;
import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.exceptions.CustomException;
import net.dunice.newsFeed.mappers.NewsMapper;
import net.dunice.newsFeed.mappers.TagsMapper;
import net.dunice.newsFeed.models.NewsEntity;
import net.dunice.newsFeed.models.TagEntity;
import net.dunice.newsFeed.models.UserEntity;
import net.dunice.newsFeed.repository.NewsRepository;
import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.BaseSuccessResponse;
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
        NewsEntity newsEntity = NewsMapper.INSTANCE.NewsDtoToNewsEntity(newsDto)
                                                        .setUser(userEntity)
                                                        .setUsername(userEntity.getName())
                                                        .setImage("" + FilesServiceImpl.uploading);
        newsRepository.save(newsEntity);
        List<TagEntity> tagList = newsDto.getTags().stream()
                                                    .map(tag -> new TagEntity().setTag(tag).setNews(newsEntity))
                                                    .collect(Collectors.toList());
        newsEntity.setTags(tagList);
        newsRepository.save(newsEntity);
        return CreateNewsSuccessResponse.CreateNewsResponse(newsEntity.getId());
    }

    @Override
    public CustomSuccessResponse<PageableResponse> getPaginatedNews(int page, int perPage, UUID userId) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        List<NewsEntity> listNewsEntity = newsRepository.findAll(pageable).getContent();
        List<GetNewsOutDto> listNewsDto = listNewsEntity.stream()
                .map(newsEntity -> NewsMapper.INSTANCE.NewsEntityToGetNewsOutDto(newsEntity)
                                .setUserId(userId)
                                .setTags(newsEntity.getTags().stream()
                                        .map(tag -> TagsMapper.INSTANCE.TagEntityToTag(tag))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
        Long numberOfElements = newsRepository.count();
        return CustomSuccessResponse.getSuccessResponse(PageableResponse.createPageableResponse(listNewsDto,
                                                                                                numberOfElements));
    }

    @Override
    public CustomSuccessResponse<PageableResponse> getPaginatedUserNews(int page, int perPage, UUID userId) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        List<NewsEntity> listNewsEntity = newsRepository.findAllByUserId(userId, pageable).getContent();
        List<GetNewsOutDto> listNewsDto = listNewsEntity.stream()
                .map(newsEntity -> NewsMapper.INSTANCE.NewsEntityToGetNewsOutDto(newsEntity)
                        .setUserId(newsEntity.getUser().getId())
                        .setTags(newsEntity.getTags().stream()
                                .map(tag -> TagsMapper.INSTANCE.TagEntityToTag(tag))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        Long numberOfElements = newsRepository.findAllByUserId(userId, pageable).getTotalElements();
        return CustomSuccessResponse.getSuccessResponse(PageableResponse.createPageableResponse(listNewsDto,
                                                                                                numberOfElements));
    }

    @Override
    public PageableResponse getFindNews(int page, int perPage, String author, String keywords, String[] tags) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        List<NewsEntity> newsEntityList = newsRepository.findAll(pageable).getContent();
        List<GetNewsOutDto> getNewsOutDtoList = newsEntityList.stream()
                .filter(newsEntity -> author == null || newsEntity.getUsername().contains(author))
                .filter(newsEntity -> keywords == null || newsEntity.getDescription().contains(keywords))
                .filter(newsEntity -> tags == null || newsEntity.getTags()
                        .stream().anyMatch(tagEntity -> List.of(tags).contains(tagEntity.getTag())))
                .map(newsEntity -> NewsMapper.INSTANCE.NewsEntityToGetNewsOutDto(newsEntity)
                        .setUserId(newsEntity.getUser().getId())
                        .setTags(newsEntity.getTags()
                                .stream().map(tagEntity -> TagsMapper.INSTANCE.TagEntityToTag(tagEntity))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        Long numberOfElements = getNewsOutDtoList.stream().count();
        return PageableResponse.createPageableResponse(getNewsOutDtoList, numberOfElements);
    }

    @Override
    public BaseSuccessResponse changeNews(Long newsId, NewsDto newsDto) {
        NewsEntity newsEntity = newsRepository.findById(newsId)
                                            .orElseThrow(() -> new CustomException(ValidationConstants.NEWS_NOT_FOUND));
        newsRepository.save(newsEntity
                .setDescription(newsDto.getDescription())
                .setImage("" + FilesServiceImpl.uploading)
                .setTitle(newsDto.getTitle())
                .setTags(newsDto.getTags().stream()
                        .map(tag -> new TagEntity()
                                .setTag(tag)
                                .setNews(newsEntity))
                        .collect(Collectors.toList())));
        return BaseSuccessResponse.getSuccessResponse();
    }

    @Override
    public BaseSuccessResponse deleteNews(Long newsId) {
        if (!newsRepository.existsById(newsId)) {
            throw new CustomException(ValidationConstants.NEWS_NOT_FOUND);
        }
        newsRepository.deleteById(newsId);
        return BaseSuccessResponse.getSuccessResponse();
    }
}
