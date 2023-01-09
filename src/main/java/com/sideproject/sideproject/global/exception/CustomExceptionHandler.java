package com.sideproject.sideproject.global.exception;

import com.sideproject.sideproject.customer.exception.UserException;
import com.sideproject.sideproject.global.response.ErrorResponseDTO;
import com.sideproject.sideproject.global.response.ResponseDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler implements ErrorController {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserException.class)
    public ErrorResponseDTO handleUserException(CustomException ex){
//        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getCustomExceptionType());
        return ErrorResponseDTO.builder()
                .errorCode(ex.getCustomExceptionType().getErrorCode())
                .message(ex.getCustomExceptionType().getErrorMsg())
                .build();
    }

}
