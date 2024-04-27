package com.vadimpoklad.clearsolution.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String massage;
    private final LocalDateTime timestamp;

    public ErrorResponse(Exception exception) {
        this.massage = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, LocalDateTime dateTime) {
        this.massage = message;
        this.timestamp = dateTime;
    }
}