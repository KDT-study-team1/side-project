package com.sideproject.sideproject.global.exception;

import com.sideproject.sideproject.user.exception.UserException;
import com.sideproject.sideproject.global.response.ErrorResponseDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
