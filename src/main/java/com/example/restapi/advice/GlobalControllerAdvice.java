package com.example.restapi.advice;

import com.example.restapi.exception.NoFoundEmployeeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus
public class GlobalControllerAdvice {
    @ExceptionHandler({NoFoundEmployeeException.class})
    public ErrorResponse handleNotFound(Exception exception){
        return new ErrorResponse(404,"Entity Not Found");
    }
}
