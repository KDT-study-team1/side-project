package com.sideproject.sideproject.like.controller;

import com.sideproject.sideproject.like.dto.LikeResponseDto;
import com.sideproject.sideproject.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
@Tag(name = "like", description = "좋아요 API")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    @Operation(summary = "좋아요 버튼",description = "좋아요가 없는 상태에서 실행하면 생기고 반대면 사라진다")
    //일단은 RequestParam으로 userId 받았다가 나중에 로그인 합쳐지면 변경예정
    //아마 @AuthenticationPrincipal 써야되는거 같음?
    public LikeResponseDto setLike(@PathVariable Long postId, @RequestParam Long userId) {
        boolean isLiked = likeService.likeButton(userId, postId);

        return LikeResponseDto.builder()
                .userId(userId)
                .postId(postId)
                .isLiked(isLiked)
                .build();
    }
}
