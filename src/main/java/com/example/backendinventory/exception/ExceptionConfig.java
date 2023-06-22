package com.example.backendinventory.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex, WebRequest wr, HttpServletRequest sr){
        ExceptionResponse er = new ExceptionResponse(sr.getMethod(), Integer.toString(HttpStatus.BAD_REQUEST.value()), LocalDateTime.now().toString(), ex.getLocalizedMessage());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }
}
