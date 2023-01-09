package com.sideproject.sideproject.user.exception;

import com.sideproject.sideproject.global.exception.CustomExceptionType;

public enum UserExceptionType implements CustomExceptionType {
    DUPLICATE_EMAIL(-101,"이미 존재하는 email입니다.");

    private int errorCode;

    private String errorMsg;

    UserExceptionType(int errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
