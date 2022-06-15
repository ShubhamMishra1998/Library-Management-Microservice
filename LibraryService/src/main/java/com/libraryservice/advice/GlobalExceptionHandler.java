package com.libraryservice.advice;
import com.libraryservice.exceptions.LibraryException;
import feign.FeignException;
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
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> error = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e -> error.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = LibraryException.class)
    public ResponseEntity<String> handelLibraryException(LibraryException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.status()))
                .body(exception.getMessage());
    }

}
