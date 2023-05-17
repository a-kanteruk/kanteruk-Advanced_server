package net.dunice.newsFeed.mappers;

import net.dunice.newsFeed.dto.Tag;
import net.dunice.newsFeed.models.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagsMapper {
    TagsMapper INSTANCE = Mappers.getMapper(TagsMapper.class);
    @Mappings({
            @Mapping(target = "id", source = "tagEntity.id"),
            @Mapping(target = "title", source = "tagEntity.tag")
    })
    Tag TagEntityToTag(TagEntity tagEntity);
}
