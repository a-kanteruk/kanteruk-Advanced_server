package net.dunice.newsFeed.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.constants.ValidationConstants;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
public class NewsDto {
    @Size(min = 3, max = 160, message = ValidationConstants.NEWS_DESCRIPTION_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.NEWS_DESCRIPTION_HAS_TO_BE_PRESENT)
    private	String description;
    @Length(min = 3, max = 130, message = ValidationConstants.NEWS_IMAGE_LENGTH)
    private String image;
    private List<@NotBlank(message = ValidationConstants.TAGS_NOT_VALID) String> tags;
    @Size(min = 3, max = 160, message = ValidationConstants.NEWS_TITLE_SIZE)
    private String title;

}
