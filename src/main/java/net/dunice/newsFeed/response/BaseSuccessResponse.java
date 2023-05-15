package net.dunice.newsFeed.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseSuccessResponse {
    private int statusCode;
    private boolean success;

    public static BaseSuccessResponse getBadResponse(int statusCode) {
        return new BaseSuccessResponse()
                .setStatusCode(statusCode)
                .setSuccess(true);
    }

    public static BaseSuccessResponse getSuccessResponse() {
        return new BaseSuccessResponse()
                .setStatusCode(1)
                .setSuccess(true);
    }
}

