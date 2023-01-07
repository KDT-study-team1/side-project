package com.sideproject.sideproject.global.exception;

public class UserException extends CustomException {
    private final UserExceptionType userExceptionType;

    public UserException(UserExceptionType exceptionType){
        this.userExceptionType = exceptionType;
    }

    @Override
    public CustomExceptionType getCustomExceptionType() {
        return this.userExceptionType;
    }
}
