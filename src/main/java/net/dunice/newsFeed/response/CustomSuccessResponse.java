package net.dunice.newsFeed.response;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.dto.LoginUserDto;

@Data
@Accessors(chain = true)
public class CustomSuccessResponse<T> {

    private T data;
    private int statusCode;
    private boolean success;

    public static <T> CustomSuccessResponse getSuccessResponse(T data){
        return new CustomSuccessResponse().setData(data)
                                            .setStatusCode(1)
                                            .setSuccess(true);
    }
    public static <T> CustomSuccessResponse getBadResponse(T data){
        return new CustomSuccessResponse().setSuccess(true).setStatusCode(1);
    }
}
