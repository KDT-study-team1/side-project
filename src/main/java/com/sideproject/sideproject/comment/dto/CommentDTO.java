package com.sideproject.sideproject.comment.dto;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.customer.domain.User;
import com.sideproject.sideproject.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long postId;
    private UserDTO userDTO;
    private String content;
    private LocalDateTime createDate;

    public static CommentDTO of(
            Long postId,
            UserDTO userDTO,
            String content
    ){
        return new CommentDTO(null, postId, userDTO, content,null);
    }

    public static CommentDTO of(
            Long id,
            Long postId,
            UserDTO userDTO,
            String content,
            LocalDateTime createDate
    ){
        return new CommentDTO(id, postId, userDTO, content, createDate);
    }


    public static CommentDTO from(Comment entity){
        return new CommentDTO(
            entity.getId(),
            entity.getPost().getId(),
            UserDTO.from(entity.getUser()), //userEntity를 userDTO로 변경
            entity.getContent(),
            entity.getCreateDate()
        );
    }

    public Comment toEntity(User user, Post post, String content){
        return Comment.of(user, post, content);
    }

}
