package com.project.alumni.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AlumniAPIException extends RuntimeException {

    @Getter
    private HttpStatus status;
    private String message;

    public AlumniAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public AlumniAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}