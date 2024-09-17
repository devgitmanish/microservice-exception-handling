package com.javatechie.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.dto.CustomErrorResponse;
import com.javatechie.exception.SwiggyServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SwiggyServiceGlobalExceptionHandler {

    @ExceptionHandler(SwiggyServiceException.class)
    public ResponseEntity<?> handleSwiggyServiceException(SwiggyServiceException ex) throws JsonProcessingException {
        log.error("SwiggyServiceGlobalExceptionHandler::handleSwiggyServiceException exception caught {}",ex.getMessage());
        CustomErrorResponse errorResponse = new ObjectMapper().readValue(ex.getMessage(), CustomErrorResponse.class);
        /*
         In the ex already get a custom JSON response comes from Restaurant Service
         we just get the message and revert as response.
         */
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
