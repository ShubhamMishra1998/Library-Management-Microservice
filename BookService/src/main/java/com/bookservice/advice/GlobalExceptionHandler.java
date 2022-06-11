package com.bookservice.advice;

import com.bookservice.exception.BookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> error=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e->error.put(e.getField(),e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = BookException.class)
    public ResponseEntity<String> handleBookException(BookException bookException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bookException.getMessage());
    }

}
