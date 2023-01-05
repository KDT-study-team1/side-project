package com.sideproject.sideproject.customer.dto;

import com.sideproject.sideproject.customer.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String email;
    private String nickname;
    private String name;
    private String phone;
    private String dongName;

    @Builder
    public UserResponseDTO(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.name = user.getUsername();
        this.phone = user.getPhoneNumber();
        this.dongName = user.getDongName();
    }
}
