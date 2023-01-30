package com.sideproject.sideproject.comment.exception;

import com.sideproject.sideproject.global.exception.CustomException;
import com.sideproject.sideproject.global.exception.CustomExceptionType;
import lombok.Getter;

@Getter
public class GlobalException extends CustomException {

    private final GlobalExceptionType globalExceptionType;

    public GlobalException(GlobalExceptionType globalExceptionType) {
        this.globalExceptionType = globalExceptionType;
    }

    @Override
    public CustomExceptionType getCustomExceptionType() {
        return this.globalExceptionType;
    }
}
