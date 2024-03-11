package com.telusko.securityApp.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExecption{

    @ExceptionHandler(UserNotFoundException.class)
    public String execptionHandler(UserNotFoundException unfe){
        return "UserNotFoundException : "+ unfe.getMessage();

    }

}
