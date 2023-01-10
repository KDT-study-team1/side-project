package com.sideproject.sideproject.comment.dto.response;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    Long id;
    String content;
    LocalDateTime createDate;
    UserDTO userDTO;

    public static CommentResponse from(CommentDTO dto) {
        return CommentResponse.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .userDTO(dto.getUserDTO())
                .build();
    }
}
