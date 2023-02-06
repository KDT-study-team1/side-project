package com.sideproject.sideproject.social.service;

import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.dto.SocialResponseDTO;

import java.util.List;

public interface SocialService {

    SocialResponseDTO createSocial(String email, SocialRequestDTO socialRequestDTO);

    List<SocialResponseDTO> socials();
}
