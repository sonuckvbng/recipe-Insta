package com.sonu.recipeinsta.exception;

public class RecipeNotFoundException extends Exception{
    public RecipeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
