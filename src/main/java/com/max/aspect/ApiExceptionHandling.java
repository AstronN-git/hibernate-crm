package com.max.aspect;

import com.max.dto.ErrorResponse;
import com.max.exception.AuthenticationException;
import com.max.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandling {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserError(UserNotFoundException exception) {
        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> authenticationError(AuthenticationException exception) {
        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnyException(Exception exception) {
        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}