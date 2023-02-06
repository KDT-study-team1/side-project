package com.sideproject.sideproject.global.exception;

import com.sideproject.sideproject.comment.exception.GlobalException;
import com.sideproject.sideproject.comment.exception.GlobalExceptionType;
import com.sideproject.sideproject.global.response.ErrorResponseDTO;
import com.sideproject.sideproject.post.exception.PostException;
import com.sideproject.sideproject.user.exception.UserException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler implements ErrorController {

    //400 에러 처리하는 핸들러
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UserException.class}) //나중에 다른 예외가 생기면 추가할 것
    public ErrorResponseDTO handleBadRequest(CustomException ex){
        return ErrorResponseDTO.builder()
                .errorCode(ex.getCustomExceptionType().getErrorCode())
                .build();
    }

    //user 에서 발생하는 예외 처리 하는 핸들러
    @ExceptionHandler(value = UserException.class)
    public ErrorResponseDTO handleUserException(CustomException ex){
        return ErrorResponseDTO.builder()
                .errorCode(ex.getCustomExceptionType().getErrorCode())
                .message(ex.getCustomExceptionType().getErrorMsg())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponseDTO handlePostException(PostException e) {
        return ErrorResponseDTO.builder()
                .errorCode(e.getCustomExceptionType().getErrorCode())
                .message(e.getCustomExceptionType().getErrorMsg())
                .build();
    }


    @ExceptionHandler
    public ErrorResponseDTO validation(ConstraintViolationException e) {
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.VALIDATION_ERROR.getErrorCode())
                .message(GlobalExceptionType.VALIDATION_ERROR.getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO validation(MethodArgumentNotValidException e) {
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.VALIDATION_ERROR.getErrorCode())
                .message(GlobalExceptionType.VALIDATION_ERROR.getErrorMsg())
                .build();
    }

    @ExceptionHandler
    public ErrorResponseDTO global(GlobalException e) {
        return ErrorResponseDTO.builder()
                .errorCode(e.getGlobalExceptionType().getErrorCode())
                .message(e.getGlobalExceptionType().getErrorMsg())
                .build();
    }


    @ExceptionHandler
    public ErrorResponseDTO exception(Exception e) {
        return ErrorResponseDTO.builder()
                .errorCode(GlobalExceptionType.INTERNAL_ERROR.getErrorCode())
                .message(GlobalExceptionType.INTERNAL_ERROR.getErrorMsg())
                .build();
    }

}
