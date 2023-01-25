package com.sideproject.sideproject.social.service;

import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.dto.SocialResponseDTO;
import com.sideproject.sideproject.social.repository.SocialRepository;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SocialResponseDTO> socials() {
        return repo.findAll().stream()
                .map(social -> SocialResponseDTO.builder()
                        .user(social.getUser())
                        .images(social.getImages())
                        .comments(social.getComments())
                        .contact(social.getContact())
                        .regionCode(social.getRegionCode())
                        .dongCode(social.getDongCode())
                        .dongName(social.getDongName())
                        .likes(social.getLikes())
                        .category(social.getCategory())
                        .socialTags(social.getSocialTags())
                        .status(social.getStatus())
                        .title(social.getTitle())
                        .hits(social.getHits())
                        .startDate(social.getStartDate())
                        .endDate(social.getEndDate())
                        .limitedNums(social.getLimitedNums())
                        .contact(social.getContact())
                        .build())
                .collect(Collectors.toList());
    }

    public Long socialUsertoDTO(User user) {
        return user.getId();
    }
}
