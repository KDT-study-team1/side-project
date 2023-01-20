package com.sideproject.sideproject.comment.exception;

import com.sideproject.sideproject.global.exception.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum GlobalExceptionType implements CustomExceptionType {

    OK(0, HttpStatus.OK, "Ok"),

    BAD_REQUEST(1000, HttpStatus.BAD_REQUEST, "Bad request"),
    VALIDATION_ERROR(1001, HttpStatus.BAD_REQUEST, "Validation error"),
    NOT_FOUND(1002, HttpStatus.NOT_FOUND, "Requested resource is not found"),

    INTERNAL_ERROR(2001, HttpStatus.INTERNAL_SERVER_ERROR, "Internal error"),
    DATA_ACCESS_ERROR(2002, HttpStatus.INTERNAL_SERVER_ERROR, "Data access error");

    private final Integer errorCode;
    private final HttpStatus httpStatus;
    private final String errorMsg;

    GlobalExceptionType(Integer errorCode, HttpStatus httpStatus, String errorMsg) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
