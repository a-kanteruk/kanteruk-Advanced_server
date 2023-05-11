package net.dunice.newsFeed.exceptions;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class CustomJwtException extends JwtException {
    public CustomJwtException(String message) {
        super(message);
    }
}
