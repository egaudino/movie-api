package com.gaudino.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Business Violation")
public class MovieBusinessException extends RuntimeException {

    public MovieBusinessException() {
        super();
    }

    public MovieBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieBusinessException(String message) {
        super(message);
    }

    public MovieBusinessException(Throwable cause) {
        super(cause);
    }
}
