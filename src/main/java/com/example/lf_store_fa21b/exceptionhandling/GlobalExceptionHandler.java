package com.example.lf_store_fa21b.exceptionhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(RessourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new java.util.Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new java.util.Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
