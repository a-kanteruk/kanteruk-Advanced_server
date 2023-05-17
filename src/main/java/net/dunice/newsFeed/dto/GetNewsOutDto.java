package net.dunice.newsFeed.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetNewsOutDto {
    private Long id;
    private String description;
    private String image;
    private List<Tag> tags;
    private String title;
    private UUID userId;
    private String username;
}
