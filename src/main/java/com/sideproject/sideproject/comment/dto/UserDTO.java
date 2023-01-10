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

    public static UserDTO of(
            Long id,
            String nickname,
            String dongName,
            String profile
    ){
        return new UserDTO(id, nickname, dongName, profile);
    }

    public static UserDTO from(User user){
        return UserDTO.of(
                user.getId(),
                user.getNickname(),
                user.getDongName(),
                user.getProfile()
        );
    }
}
