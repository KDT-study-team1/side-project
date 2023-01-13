package com.sideproject.sideproject.user.dto;

import com.sideproject.sideproject.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Schema(description = "회원 조회시 출력할 DTO")
@Builder
@ToString
public class UserResponseDTO {
    private String email;
    private String nickname;
    private String name;
    private String phone;
    private String dongName;

    public UserResponseDTO(){

    }
    @Builder
    public UserResponseDTO(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.name = user.getUsername();
        this.phone = user.getPhoneNumber();
        this.dongName = user.getDongName();
    }
}
