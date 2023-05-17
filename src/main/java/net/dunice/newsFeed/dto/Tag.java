package net.dunice.newsFeed.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tag {
    private Long id;
    private String title;
}
