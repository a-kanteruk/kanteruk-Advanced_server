package net.dunice.newsFeed.exceptions;

import net.dunice.newsFeed.constants.ErrorCodes;
import net.dunice.newsFeed.response.CustomSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(MethodArgumentNotValidException mavex) {
        List<Integer> codes = mavex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> ErrorCodes.getErrorCode(e.getDefaultMessage()))
                .toList();
        return new ResponseEntity(CustomSuccessResponse.getBadResponse(codes.get(0)),
                HttpStatus.BAD_REQUEST);
    }
}
