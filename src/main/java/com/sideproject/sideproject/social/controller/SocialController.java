package com.sideproject.sideproject.social.controller;

import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    @PostMapping("/social")
    public String createSocial(SocialRequestDTO socialRequestDTO) {
        return socialService.createSocial(socialRequestDTO);
    }
}
