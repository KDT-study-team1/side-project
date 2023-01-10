package com.sideproject.sideproject.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDTO<T> {

    @Schema(name = "Http code", example = "200")
    private int state;

    @Schema(name = "result code", example = "success")
    private String result;

    @Schema(name = "result message", example = "응답 성공")
    private String message;

    @Schema(name = "result date", example = "{name = '철수김', age = '25세'}")
    private T data;

    public ResponseDTO(int state, String result, String message, T data) {
        this.state = state;
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(int state, String result, String message) {
        this.state = state;
        this.result = result;
        this.message = message;
    }
}
