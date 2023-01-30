package com.sideproject.sideproject.comment.dto.request;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.CommentUserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 작성 시 입력받는 DTO")
public class CommentRequest {
    @Schema(description = "댓글 달 post Id", defaultValue = "1")
    @NotNull @Positive
    Long postId;
    @Schema(description = "댓글 내용", defaultValue = "유익해요!")
    @NotBlank
    @Size(max = 500)
    String content;
    @Schema(description = "부모 댓글 Id", defaultValue = "2")
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
