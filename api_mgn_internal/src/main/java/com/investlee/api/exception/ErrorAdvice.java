package com.investlee.api.exception;

import com.investlee.api.user.AlreadyAgreedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(AlreadyAgreedException.class)
    public ResponseEntity<?> handleAlreadyAgreedException() {
        return ResponseEntity
                .badRequest()
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException() {
        return ResponseEntity
                .notFound()
                .build();
    }

}