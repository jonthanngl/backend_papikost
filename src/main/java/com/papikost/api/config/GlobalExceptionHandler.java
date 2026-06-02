package com.papikost.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        
        kingEmyuResponse.put("success", false);
        kingEmyuResponse.put("error", "Terjadi kesalahan pada server!");
        kingEmyuResponse.put("detail", ex.getMessage());

        // Mengembalikan HTTP Status 500 (Internal Server Error) dengan format JSON
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(kingEmyuResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        
        kingEmyuResponse.put("success", false);
        kingEmyuResponse.put("error", ex.getMessage());

        // Mengembalikan HTTP Status 400 (Bad Request) untuk error logika bisnis
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(kingEmyuResponse);
    }
}