package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class DeviceAlreadyExists extends CustomException{
    private static final String MESSAGE = "Device already exists!";
    private static final HttpStatus httpStatus = HttpStatus.CONFLICT;
    public DeviceAlreadyExists() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
