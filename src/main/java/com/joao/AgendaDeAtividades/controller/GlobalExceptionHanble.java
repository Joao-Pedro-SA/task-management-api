package com.joao.AgendaDeAtividades.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.joao.AgendaDeAtividades.exceptions.ActivityNotFoundException;
import com.joao.AgendaDeAtividades.exceptions.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHanble {

    @ExceptionHandler(ActivityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleActivityNotFound(
        ActivityNotFoundException ex,
        HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse error = new ErrorResponse(LocalDateTime.now(),
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
         request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }






}
