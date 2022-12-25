package com.sommelier.wine4you.exception;

import org.springframework.http.HttpStatus;

public class WineApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public WineApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public WineApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
