package com.sideproject.sideproject.post.exception;

import com.sideproject.sideproject.global.exception.CustomException;
import com.sideproject.sideproject.global.exception.CustomExceptionType;


public class PostException extends CustomException {

    private final PostExceptionType postExceptionType;

    public PostException(PostExceptionType postExceptionType){
        this.postExceptionType = postExceptionType;
    }

    @Override
    public CustomExceptionType getCustomExceptionType() {
        return this.postExceptionType;
    }
}
