package com.sideproject.sideproject.comment.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    Long postId;
    String content;

    // TODO: 2023-01-09 대댓글 구현할때 parentCommentId 추가

    public static CommentRequest of(Long postId, String content){
        return new CommentRequest(postId, content);
    }


}
