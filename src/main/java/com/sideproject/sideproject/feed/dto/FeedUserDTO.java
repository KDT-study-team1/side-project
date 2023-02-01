package com.sideproject.sideproject.feed.dto;

import com.sideproject.sideproject.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeedUserDTO {

    private Long id;
    private String nickname;
    private String dongName;
    private String profile;

    public static FeedUserDTO from(User user){
        return FeedUserDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .dongName(user.getDongName())
                .profile(user.getProfile())
                .build();
    }
}
