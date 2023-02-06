package com.sideproject.sideproject.social.controller;

import com.sideproject.sideproject.global.response.ResponseDTO;
import com.sideproject.sideproject.social.dto.SocialDetailResponseDTO;
import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.dto.SocialResponseDTO;
import com.sideproject.sideproject.social.service.SocialService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    @PostMapping("/social")
    @Operation(summary = "모임 게시글 생성", description = "모임 게시글 생성 api")
    public ResponseDTO<?> createSocial(@RequestBody SocialRequestDTO socialRequestDTO) {
        String email = "test@email.com"; // 임시
        SocialResponseDTO socialResponseDTO = socialService.createSocial(email, socialRequestDTO);

        return new ResponseDTO<>(socialResponseDTO);
    }

    @GetMapping("/socials")
    @Operation(summary = "모임 게시글 전체 조회", description = "모임 게시글 전체 조회 api")
    public ResponseDTO<?> socials() {
        List<SocialResponseDTO> socials = socialService.socials();

        return new ResponseDTO<>(socials);
    }

    @GetMapping("/social/{socialId}")
    @Operation(summary = "모임 게시글 상세 정보 조회", description = "모임 게시글 상세 정보 조회 api")
    public ResponseDTO<?> socialDetail(@PathVariable Long socialId) {
        SocialDetailResponseDTO social = socialService.socialDetail(socialId);

        return new ResponseDTO<>(social);
    }
}
