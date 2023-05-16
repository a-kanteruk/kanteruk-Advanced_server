package net.dunice.newsFeed.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NewsDto {
    @Length(min = 3, max = 160)
    private	String description;
    @Length(min = 3, max = 130)
    private String image;
    private String tags; //other Table
    @Length(min = 3, max = 160)
    private String title;

}
