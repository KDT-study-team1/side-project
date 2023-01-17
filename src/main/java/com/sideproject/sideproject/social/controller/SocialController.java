package com.sideproject.sideproject.social.controller;

import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.dto.SocialResponseDTO;
import com.sideproject.sideproject.social.service.SocialService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    @PostMapping("/social")
    @Operation(summary = "모임 게시글 생성", description = "모임 게시글 생성 api")
    public String createSocial(SocialRequestDTO socialRequestDTO) {
        return socialService.createSocial(socialRequestDTO);
    }

    @GetMapping("/socials")
    @Operation(summary = "모임 게시글 전체 조회", description = "모임 게시글 전체 조회 api")
    public List<SocialResponseDTO> socials() {
        return socialService.socials();
    }
}
