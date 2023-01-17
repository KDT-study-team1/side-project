package com.sideproject.sideproject.comment.dto;

import com.sideproject.sideproject.user.domain.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentUserDTO {
    private Long id;
    private String nickname;
    private String dongName;
    private String profile;

    public static CommentUserDTO from(User user) {
        return CommentUserDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .dongName(user.getDongName())
                .profile(user.getProfile())
                .build();
    }
}
