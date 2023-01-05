package com.sideproject.sideproject.customer.dto;

import com.sideproject.sideproject.customer.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema
@Data
@Builder
public class UserRequestDTO {
    @Schema(description = "사용자 이메일", defaultValue = "test@email.com")
    private String email;
    @Schema(description = "비밀번호", defaultValue = "test1234")
    private String password;
    @Schema(description = "사용자 본명", defaultValue = "김대수다")
    private String name;
    @Schema(description = "폰번호", defaultValue = "010-1234-1234")
    private String phone;
    @Schema(description = "사용자 닉네임", defaultValue = "테스터")
    private String nickName;
    @Schema(description = "지역 번호", defaultValue = "1111010400")
    private Long regionCode;
    @Schema(description = "행정구역 명", defaultValue = "서울특별시 종로구 효자동")
    private String regionName;


    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .dongCode(regionCode)
                .dongName(regionName)
                .nickname(nickName)
                .phoneNumber(phone)
                .username(name)
                .build();
    }
}
