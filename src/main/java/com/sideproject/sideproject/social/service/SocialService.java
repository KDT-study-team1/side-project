package com.sideproject.sideproject.social.service;

import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.dto.SocialResponseDTO;

import java.util.List;

public interface SocialService {

    String createSocial(SocialRequestDTO socialRequestDTO);

    List<SocialResponseDTO> socials();
}
