package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class InvalidRoleException extends CustomException{
    private static final String MESSAGE = "Invalid Role!";
    private static final HttpStatus httpStatus = HttpStatus.FORBIDDEN;
    public InvalidRoleException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
