package net.dunice.newsFeed.response;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.newsFeed.dto.LoginUserDto;

@Data
@Accessors(chain = true)
public class CustomSuccessResponse {

    LoginUserDto data;
    int statusCode;
    boolean success;

    public static CustomSuccessResponse getSuccessResponse(LoginUserDto loginUserDto){
        return new CustomSuccessResponse().setData(loginUserDto)
                                            .setStatusCode(1)
                                            .setSuccess(true);
    }
    public static CustomSuccessResponse getBadResponse(String message){
        return new CustomSuccessResponse().setSuccess(false).setStatusCode(0);
    }
}
