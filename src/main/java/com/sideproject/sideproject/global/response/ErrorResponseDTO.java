package com.sideproject.sideproject.global.response;

import com.sideproject.sideproject.global.exception.CustomExceptionType;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponseDTO {
    private int errorCode;
    private String message;

}
