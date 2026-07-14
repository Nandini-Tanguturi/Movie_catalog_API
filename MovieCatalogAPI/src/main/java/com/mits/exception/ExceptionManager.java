package com.mits.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(DirectorNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDirectorException(DirectorNotFoundException e) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", LocalDateTime.now());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("error", "Not Found");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGenreException(GenreNotFoundException e) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", LocalDateTime.now());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("error", "Not Found");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMovieException(MovieNotFoundException e) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", LocalDateTime.now());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("error", "Not Found");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxGenresExceededException.class)
    public ResponseEntity<Map<String, Object>> handleGenreLimitException(MaxGenresExceededException e) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", LocalDateTime.now());
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("error", "Bad Request");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}