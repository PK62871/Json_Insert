package com.mavencheck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleDuplicateException(DuplicateUserException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.ZonedDateTime.now().toString());
        response.put("status", 409);
        response.put("error", "Conflict");
        response.put("message", ex.getMessage());
        response.put("path", "/api/users");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.ZonedDateTime.now().toString());
        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());
        response.put("path", "/api/users");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernotFoundException.class)
    public ResponseEntity<?> handleNotFound(UsernotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.ZonedDateTime.now().toString());
        response.put("status", 404);
        response.put("error", "Not Found");
        response.put("message", ex.getMessage());
        response.put("path", "/api/users");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
