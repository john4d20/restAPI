package com.example.restapi;

public class NoFoundEmployeeException extends RuntimeException {
    public NoFoundEmployeeException() {
        super("Employee not found.");
    }
}
