package com.sonu.recipeinsta.exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }

}
