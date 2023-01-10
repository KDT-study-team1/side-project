package com.sideproject.sideproject.comment.dto;

import com.sideproject.sideproject.customer.domain.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String nickname;
    private String dongName;
    private String profile;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .dongName(user.getDongName())
                .profile(user.getProfile())
                .build();
    }
}
