package net.dunice.newsFeed.mappers;

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
            @Mapping(target = "image", source = "newsDto.image"),
            @Mapping(target = "tags", ignore = true),
            @Mapping(target = "title", source = "newsDto.title"),
            @Mapping(target = "user", ignore = true)
    })
    NewsEntity NewsDtoToNewsEntity(NewsDto newsDto);
}
