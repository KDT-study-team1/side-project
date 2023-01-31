package com.sideproject.sideproject.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LikeDto {
    private Long postId;
    private Long userId;
    private boolean isLiked;
}
