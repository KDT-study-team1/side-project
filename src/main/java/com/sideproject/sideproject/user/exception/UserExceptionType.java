package com.sideproject.sideproject.user.exception;

import com.sideproject.sideproject.global.exception.CustomExceptionType;

public enum UserExceptionType implements CustomExceptionType {
    DUPLICATE_EMAIL(-101,"이미 존재하는 `email`입니다."),
    NON_EXISTENT_USER(-102, "존재하지 않는 회원입니다."),
    UNMATCHED_PASSWORD(-103, "일치하지 않는 패스워드"),
    THERE_ARE_NO_TOKEN_ERROR(-104, "토큰이 생성되지 않았습니다."),
    PARSING_FAIL(-105, "토큰 파싱에 실패했습니다.");

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
