package com.sideproject.sideproject.global.response;

import com.sideproject.sideproject.customer.exception.UserExceptionType;
import com.sideproject.sideproject.global.exception.CustomExceptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private int errorCode;
    private String message;

    public ErrorResponseDTO(CustomExceptionType ex){
        this.errorCode = ex.getErrorCode();
        this.message = ex.getErrorMsg();
    }
}
