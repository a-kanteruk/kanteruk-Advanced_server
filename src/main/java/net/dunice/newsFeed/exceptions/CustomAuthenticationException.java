package net.dunice.newsFeed.exceptions;

public class CustomAuthenticationException extends RuntimeException{

    public CustomAuthenticationException(String message) {
        super(message);
    }
}