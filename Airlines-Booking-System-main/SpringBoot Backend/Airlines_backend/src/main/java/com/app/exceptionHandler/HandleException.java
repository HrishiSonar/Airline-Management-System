package com.app.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleException {

    @ExceptionHandler(Throwable.class)
    public Object handleNotFoundException(Throwable ex) {
        System.out.println("Stack Trace: \n\n");
        ex.printStackTrace();
        System.out.println("Err Msg:\n\n" + ex.getMessage() + "\n\n");
        System.out.println("Err Cause: \n\n" + ex.getCause() + "\n\n");
        return ex.getCause();
    }

    // Add more exception handler methods for other custom exceptions
}