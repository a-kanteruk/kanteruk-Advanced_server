package net.dunice.newsFeed.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomSuccessResponse<T> {

    private T data;
    private int statusCode;
    private boolean success;
    private List<Integer> codes;

    public static <T> CustomSuccessResponse getSuccessResponse(T data) {
        return new CustomSuccessResponse().setData(data)
                                            .setStatusCode(1)
                                            .setSuccess(true);
    }
    public static <T> CustomSuccessResponse getBadResponse(List<Integer> codes, int statusCode) {
        return new CustomSuccessResponse().setCodes(codes).setSuccess(true).setStatusCode(statusCode);
    }

    public static <T> CustomSuccessResponse getBadResponse(int statusCode) {
        return new CustomSuccessResponse().setSuccess(true).setStatusCode(statusCode);
    }
}
