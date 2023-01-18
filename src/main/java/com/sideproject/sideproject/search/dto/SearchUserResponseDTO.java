package com.sideproject.sideproject.search.dto;

import com.sideproject.sideproject.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchUserResponseDTO {
    private String id;

    private String email;

    private String username;

    private String nickname;

    private String dongCode;

    private String dongName;

    @Builder
    public SearchUserResponseDTO(User user){
        this.id = String.valueOf(user.getId());
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.dongCode = String.valueOf(user.getDongCode());
        this.dongName = user.getDongName();
    }
}
