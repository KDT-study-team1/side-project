package com.sideproject.sideproject.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LoginRequestDTO {

    @Schema(defaultValue = "test@email.com")
    private String email;
    @Schema(defaultValue = "test1234")
    private String password;
}
