package com.sideproject.sideproject.comment.exception;

import com.sideproject.sideproject.global.exception.CustomException;
import com.sideproject.sideproject.global.response.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler
    public ErrorResponseDTO validation(ConstraintViolationException e) {
        log.error("error name : {}",e.toString());
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.VALIDATION_ERROR.getErrorCode())
                .message(GlobalExceptionType.VALIDATION_ERROR.getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO validation(MethodArgumentNotValidException e) {
        log.error("error name : {}",e.toString());
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.VALIDATION_ERROR.getErrorCode())
                .message(GlobalExceptionType.VALIDATION_ERROR.getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO global(GlobalException e) {
        log.error("error name : {}",e.getGlobalExceptionType().getErrorMsg());
        return ErrorResponseDTO.builder()
                .errorCode(e.getGlobalExceptionType().getErrorCode())
                .message(e.getGlobalExceptionType().getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO global(CustomException e) {
        log.error("error name : {}",e.getCustomExceptionType().getErrorMsg());
        return ErrorResponseDTO.builder()
                .errorCode(e.getCustomExceptionType().getErrorCode())
                .message(e.getCustomExceptionType().getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO exception(Exception e) {
        log.error("error name : {}",e.toString());
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.INTERNAL_ERROR.getErrorCode())
                .message(GlobalExceptionType.INTERNAL_ERROR.getErrorMsg())
                .build();
    }


}

