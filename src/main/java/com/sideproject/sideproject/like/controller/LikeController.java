package com.sideproject.sideproject.like.controller;

import com.sideproject.sideproject.like.dto.LikeDto;
import com.sideproject.sideproject.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like/{postId}")
    //일단은 RequestParam으로 userId 받았다가 나중에 로그인 합쳐지면 변경예정
    //아마 @AuthenticationPrincipal 써야되는거 같음?
    public LikeDto setLike(@PathVariable Long postId, @RequestParam Long userId) {
        boolean isLiked = likeService.likeButton(userId, postId);

        return new LikeDto(userId, postId, isLiked);
    }
}
