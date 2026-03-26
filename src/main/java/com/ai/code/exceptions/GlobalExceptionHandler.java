package com.ai.code.exceptions;

import com.ai.code.controllers.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProblemNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProblemNotFoundException(ProblemNotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()),ex.getStatusCode());
    }
}
