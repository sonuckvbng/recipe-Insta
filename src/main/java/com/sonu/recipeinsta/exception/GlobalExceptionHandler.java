package com.sonu.recipeinsta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public static ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception){
        ExceptionFormat exceptionFormat = ExceptionFormat.builder().errorStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .description(exception.getMessage())
                .cause(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionFormat,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public static ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception){
        ExceptionFormat exceptionFormat = ExceptionFormat.builder().errorStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .description(exception.getMessage())
                .cause(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionFormat,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public static ResponseEntity<Object> handleRecipeNotFoundException(RecipeNotFoundException exception){
        ExceptionFormat exceptionFormat = ExceptionFormat.builder().errorStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .description(exception.getMessage())
                .cause(exception.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(exceptionFormat,HttpStatus.NOT_FOUND);
    }
}
