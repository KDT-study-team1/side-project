package com.sideproject.sideproject.global.exception;

public enum UserExceptionType implements CustomExceptionType {
    DUPLICATE_EMAIL(-301,"이미 존재하는 email입니다.");

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
