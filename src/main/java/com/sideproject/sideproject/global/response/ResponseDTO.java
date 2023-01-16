package com.sideproject.sideproject.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ResponseDTO<T> {

    @Schema(name = "success", example = "true")
    private Boolean success;

    @Schema(name = "Http code", example = "200")
    private Integer code;
    @Schema(name = "result message", example = "응답 성공")
    private String message;

    @Schema(name = "result date", example = "{name = '철수김', age = '25세'}")
    private T data;

    public ResponseDTO(T data) {
        this.success = true;
        this.code = HttpStatus.OK.value();
        this.message = "요청에 성공하였습니다.";
        this.data = data;
    }

}
