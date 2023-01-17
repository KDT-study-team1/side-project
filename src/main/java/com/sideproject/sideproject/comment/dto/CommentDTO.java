package com.sideproject.sideproject.comment.dto;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.customer.domain.User;
import com.sideproject.sideproject.post.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long postId;
    private CommentUserDTO commentUserDTO;
    private String content;
    private Long parentCommentId;
    private LocalDateTime createDate;
    private Boolean deleted;

    public static CommentDTO from(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .postId(entity.getPost().getId())
                .commentUserDTO(CommentUserDTO.from(entity.getUser())) //엔티티 dto로 변경해서 넣음
                .content(entity.getContent())
                .parentCommentId(entity.getParentCommentId())
                .createDate(entity.getCreateDate())
                .deleted(entity.getDeleted())
                .build();
    }

    public Comment toEntity(User user, Post post, String content, Long parentCommentId) {
        return Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .parentCommentId(parentCommentId)
                .build();
    }

}
