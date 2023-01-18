package com.sideproject.sideproject.user.exception;

import com.sideproject.sideproject.global.exception.CustomException;
import com.sideproject.sideproject.global.exception.CustomExceptionType;

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
