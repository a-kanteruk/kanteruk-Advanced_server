package net.dunice.newsFeed.responses;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.dto.GetNewsOutDto;

@Data
@Accessors(chain = true)
public class PageableResponse {
    private List<GetNewsOutDto> content;
    private Long numberOfElements;

    public static PageableResponse createPageableResponse(List<GetNewsOutDto> list, Long numberOfElements) {
        return new PageableResponse().setContent(list)
                                     .setNumberOfElements(numberOfElements);
    }
}
