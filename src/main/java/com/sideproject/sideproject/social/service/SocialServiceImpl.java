package com.sideproject.sideproject.social.service;

import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final SocialRepository repo;

    @Override
    public String createSocial(SocialRequestDTO socialRequestDTO) {
        if (socialRequestDTO != null) {
            repo.save(socialRequestDTO.toEntity());

            return "success";
        }

        return "failed";
    }
}
