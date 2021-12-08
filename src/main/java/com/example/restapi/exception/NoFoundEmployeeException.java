package com.example.restapi.exception;

public class NoFoundEmployeeException extends RuntimeException {
    public NoFoundEmployeeException() {
        super("Employee not found.");
    }
}
