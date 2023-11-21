package com.alumni.userservice.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class GlobalAPIException extends RuntimeException {

    @Getter
    private HttpStatus status;
    private String message;

    public GlobalAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public GlobalAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}