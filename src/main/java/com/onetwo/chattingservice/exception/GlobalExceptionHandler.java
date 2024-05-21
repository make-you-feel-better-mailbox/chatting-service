package com.onetwo.chattingservice.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onetwo.mailboxcommonconfig.common.exceptions.BadRequestException;
import onetwo.mailboxcommonconfig.common.exceptions.BadResponseException;
import onetwo.mailboxcommonconfig.common.exceptions.NotFoundResourceException;
import onetwo.mailboxcommonconfig.common.exceptions.TokenValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException e) {
        log.error("BadRequestException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(BadResponseException.class)
    public ResponseEntity<String> badResponseException(BadResponseException e) {
        log.error("BadResponseException", e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<String> tokenValidationException(TokenValidationException e) {
        log.debug("TokenValidationException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<String> notFoundResourceException(NotFoundResourceException e) {
        log.debug("NotFoundResourceException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.debug("MethodArgumentNotValidException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.debug("HttpMessageNotReadableException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
