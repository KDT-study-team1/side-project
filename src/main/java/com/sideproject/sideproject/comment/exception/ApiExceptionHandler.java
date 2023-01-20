package com.sideproject.sideproject.comment.exception;

import com.sideproject.sideproject.global.exception.CustomException;
import com.sideproject.sideproject.global.response.ErrorResponseDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ErrorResponseDTO validation(ConstraintViolationException e, WebRequest request) {
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.VALIDATION_ERROR.getErrorCode())
                .message(GlobalExceptionType.VALIDATION_ERROR.getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO validation(MethodArgumentNotValidException e, WebRequest request) {
        System.out.println("methodArgumentNotValidException");
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.VALIDATION_ERROR.getErrorCode())
                .message(GlobalExceptionType.VALIDATION_ERROR.getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO global(GlobalException e, WebRequest request) {
        System.out.println("global");
        return ErrorResponseDTO.builder()
                .errorCode(e.getGlobalExceptionType().getErrorCode())
                .message(e.getGlobalExceptionType().getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO global(CustomException e, WebRequest request) {
        System.out.println("global");
        return ErrorResponseDTO.builder()
                .errorCode(e.getCustomExceptionType().getErrorCode())
                .message(e.getCustomExceptionType().getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO exception(Exception e, WebRequest request) {
        System.out.println("exception");
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.INTERNAL_ERROR.getErrorCode())
                .message(GlobalExceptionType.INTERNAL_ERROR.getErrorMsg())
                .build();
    }


}

