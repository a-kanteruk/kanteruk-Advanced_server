package net.dunice.newsFeed.exceptions;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import net.dunice.newsFeed.constants.ErrorCodes;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        return new ResponseEntity(CustomSuccessResponse.getBadResponse(codes, codes.get(0)),
                                                                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException exception) {
        List<Integer> codes = exception.getConstraintViolations()
                .stream()
                .map(x -> ErrorCodes.getErrorCode(x.getMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity(CustomSuccessResponse.getBadResponse(codes, codes.get(0)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handle(CustomException exception) {
        return new ResponseEntity(CustomSuccessResponse.getBadResponse(ErrorCodes.getErrorCode(exception.getMessage())),
                                                                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity handle(FileNotFoundException exception) {
        return new ResponseEntity(CustomSuccessResponse.getBadResponse(ErrorCodes
                                            .getErrorCode(ValidationConstants.EXCEPTION_HANDLER_NOT_PROVIDED)),
                                                                                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity handle(FileUploadException exception) {
        return new ResponseEntity(CustomSuccessResponse.getBadResponse(ErrorCodes
                                                   .getErrorCode(ValidationConstants.UNKNOWN)), HttpStatus.BAD_REQUEST);
    }
}
