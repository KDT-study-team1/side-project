package com.sideproject.sideproject.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseDTO<T> {

    @Schema(name = "Http code", example = "200")
    private Integer code;
    @Schema(name = "result message", example = "응답 성공")
    private String message;

    @Schema(name = "result date", example = "{name = '철수김', age = '25세'}")
    private T data;

    public ResponseDTO(T data) {
        this.code = HttpStatus.OK.value();
        this.message = "요청에 성공하였습니다.";
        this.data = data;
    }

    public static <T> ResponseDTO<T> empty() {
        return new ResponseDTO<>(null);
    }

}
