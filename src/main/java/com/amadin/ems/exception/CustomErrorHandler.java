package com.amadin.ems.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<?> handleNotFoundExceptions(ResourceNotFoundException exception, WebRequest request) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.NOT_FOUND);
        errors.put("message", exception.getMessage());
        errors.put("timestamp", Instant.now());
        errors.put("path", request.getDescription(false));

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { BadRequestException.class, DataIntegrityViolationException.class })
    public ResponseEntity<?> handleBadRequestExceptions(BadRequestException exception, WebRequest request) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST);
        errors.put("message", exception.getMessage());
        errors.put("timestamp", Instant.now());
        errors.put("path", request.getDescription(false));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
