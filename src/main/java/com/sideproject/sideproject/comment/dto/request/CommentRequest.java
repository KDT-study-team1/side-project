package com.sideproject.sideproject.comment.dto.request;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.CommentUserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    Long postId;
    String content;
    Long parentCommentId;


    public CommentDTO toDTO(CommentUserDTO commentUserDTO) {
        return CommentDTO.builder()
                .postId(this.postId)
                .commentUserDTO(commentUserDTO)
                .content(this.content)
                .parentCommentId(this.parentCommentId)
                .build();
    }
}
