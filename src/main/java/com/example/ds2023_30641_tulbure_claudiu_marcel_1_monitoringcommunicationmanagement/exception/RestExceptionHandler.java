package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception;


import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { RuntimeException.class})
    public ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        log.error(ex.getMessage());
        log.error(request.getDescription(false));

        String bodyOfResponse = "Internal Server error";
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "There is an internal error! Please contact out staff.",
                Collections.singleton(bodyOfResponse));
        return handleExceptionInternal(ex, errorInformation,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler(value
            = { AuthenticationException.class})
    public ResponseEntity<Object> handleConflict2(
            RuntimeException ex, WebRequest request) {

        log.error(ex.getMessage());
        log.error(request.getDescription(false));

        String bodyOfResponse = "Bad credentials";
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Collections.singleton(bodyOfResponse));
        return handleExceptionInternal(ex, errorInformation,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(value
            = { DeviceNotFoundException.class})
    public ResponseEntity<Object> handleConflict12(
            CustomException ex, WebRequest request) {


        String bodyOfResponse = "Device not found!";
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(
                ex.getStatus().getReasonPhrase(),
                ex.getStatus().value(),
                ex.getMessage(),
                Collections.singleton(bodyOfResponse));
        return handleExceptionInternal(
                ex,
                errorInformation,
                new HttpHeaders(),
                ex.getStatus(),
                request
        );
    }

    @ExceptionHandler(value
            = { JsonProcessingException.class})
    public ResponseEntity<Object> handleParseJSONException(
            CustomException ex, WebRequest request) {

        String bodyOfResponse = "Device parsing to json error ";
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(
                ex.getStatus().getReasonPhrase(),
                ex.getStatus().value(),
                ex.getMessage(),
                Collections.singleton(bodyOfResponse));

        log.error(ex.getMessage());
        return handleExceptionInternal(
                ex,
                errorInformation,
                new HttpHeaders(),
                ex.getStatus(),
                request
        );
    }


}