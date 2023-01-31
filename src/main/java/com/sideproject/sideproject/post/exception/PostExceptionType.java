package com.sideproject.sideproject.post.exception;

import com.sideproject.sideproject.global.exception.CustomExceptionType;

public enum PostExceptionType implements CustomExceptionType {

    NON_EXISTENT_POST(-201, "존재하지 않는 포스트입니다.");

    private int errorCode;
    private String errorMsg;

    PostExceptionType(int errorCode, String errorMsg) {
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
