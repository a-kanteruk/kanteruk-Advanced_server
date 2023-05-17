package net.dunice.newsFeed.responses;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.dto.GetNewsOutDto;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageableResponse {
    private List<GetNewsOutDto> content;
    private Long numberOfElements;

    public static PageableResponse createPageableResponse(List<GetNewsOutDto> list, Long numberOfElements){
        return new PageableResponse().setContent(list)
                                     .setNumberOfElements(numberOfElements);
    }
}
