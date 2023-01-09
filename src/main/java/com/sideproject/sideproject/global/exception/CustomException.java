package com.sideproject.sideproject.global.exception;

public abstract class CustomException extends RuntimeException{

    public abstract CustomExceptionType getCustomExceptionType();

}
