package com.sideproject.sideproject.comment.dto.request;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    Long postId;
    String content;
    Long parentCommentId;

    //TODO: 2023-01-09 대댓글 구현할때 parentCommentId 추가


    public CommentDTO toDTO(UserDTO userDTO) {
        return CommentDTO.builder()
                .postId(this.postId)
                .userDTO(userDTO)
                .content(this.content)
                .parentCommentId(this.parentCommentId)
                .build();
    }
}
