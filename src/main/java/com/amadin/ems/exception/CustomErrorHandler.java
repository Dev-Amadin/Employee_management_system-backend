package com.amadin.ems.exception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<?> handleNotFoundExceptions(ResourceNotFoundException exception, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                request.getDescription(false),
                new Date());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { BadRequestException.class, DataIntegrityViolationException.class })
    public ResponseEntity<?> handleBadRequestExceptions(BadRequestException exception, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                request.getDescription(false),
                new Date());

        // Map<String, Object> errors = new HashMap<>();
        // errors.put("status", HttpStatus.BAD_REQUEST);
        // errors.put("message", exception.getMessage());
        // errors.put("timestamp", Instant.now());
        // errors.put("path", request.getDescription(false));
        // errors.put("code", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
            WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                request.getDescription(false),
                new Date());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
