package net.dunice.newsFeed.responses;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateNewsSuccessResponse {
    private Long id;
    private int statusCode;
    private boolean success;

    public static CreateNewsSuccessResponse CreateNewsResponse(Long id, int statusCode){
        return new CreateNewsSuccessResponse().setId(id)
                                              .setStatusCode(statusCode)
                                              .setSuccess(true);
    }
}
