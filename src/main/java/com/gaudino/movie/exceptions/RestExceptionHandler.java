package com.gaudino.movie.exceptions;

import com.gaudino.movie.utils.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = MovieNotFoundException.class)
    public ResponseEntity<Object> handleMovieNotFoundException(MovieNotFoundException exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ApiError apiError = new ApiError("Movie Not Found", details);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MovieBusinessException.class)
    public ResponseEntity<Object> handleMovieBusinessException(MovieBusinessException exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ApiError apiError = new ApiError("Validation error", details);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ApiError apiError = new ApiError("Server Error", details);

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
