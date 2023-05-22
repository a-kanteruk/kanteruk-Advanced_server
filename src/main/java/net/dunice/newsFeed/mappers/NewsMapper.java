package net.dunice.newsFeed.mappers;

import net.dunice.newsFeed.dto.GetNewsOutDto;
import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.models.NewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "description", source = "newsDto.description"),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "tags", ignore = true),
            @Mapping(target = "title", source = "newsDto.title"),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "username", ignore = true)
    })
    NewsEntity NewsDtoToNewsEntity(NewsDto newsDto);

    @Mappings({
            @Mapping(target = "id", source = "newsEntity.id"),
            @Mapping(target = "description", source = "newsEntity.description"),
            @Mapping(target = "image", source = "newsEntity.image"),
            @Mapping(target = "tags", ignore = true),
            @Mapping(target = "title", source = "newsEntity.title"),
            @Mapping(target = "username", source = "newsEntity.username"),
            @Mapping(target = "userId", ignore = true)
    })
    GetNewsOutDto NewsEntityToGetNewsOutDto(NewsEntity newsEntity);
}
