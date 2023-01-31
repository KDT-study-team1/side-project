package com.sideproject.sideproject.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "좋아요 버튼 실행 시 response DTO")
public class LikeDto {
    @Schema(description = "좋아요가 남겨진 Post Id")
    private Long postId;
    @Schema(description = "좋아요를 누른 User Id")
    private Long userId;
    @Schema(description = "true면 좋아요가 눌러진 상태 false면 해제된 상태")
    private boolean isLiked;
}
