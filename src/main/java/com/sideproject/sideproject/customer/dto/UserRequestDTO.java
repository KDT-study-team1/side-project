package com.sideproject.sideproject.customer.dto;

import com.sideproject.sideproject.customer.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Table;

@Schema
@Setter
@Builder
public class UserRequestDTO {
    @Schema(name = "사용자 이메일")
    private String email;
    @Schema(name = "비밀번호")
    private String password;
    @Schema(name = "사용자 본명")
    private String name;
    @Schema(name = "폰번호")
    private String phone;
    @Schema(name = "사용자 닉네임")
    private String nickName;
    @Schema(name = "동까지 나오는 행정구역코드")
    private int regionCode;
    @Schema(name = "시까지 나오는 행정구역코드")
    private int dongCode;
    @Schema(name = "사용자 주소")
    private String region;

}
