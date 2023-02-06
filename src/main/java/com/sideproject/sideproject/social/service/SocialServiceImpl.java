package com.sideproject.sideproject.social.service;

import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.social.dto.SocialRequestDTO;
import com.sideproject.sideproject.social.dto.SocialResponseDTO;
import com.sideproject.sideproject.social.repository.SocialRepository;
import com.sideproject.sideproject.tag.domain.Category;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final SocialRepository repo;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public SocialResponseDTO createSocial(String email, SocialRequestDTO socialRequestDTO) {
        User user = userRepository.findByEmail(email).orElse(null);
        Social social = repo.save(socialRequestDTO.toEntity(user));

        return SocialResponseDTO.builder()
                .userId(userToUserId(social.getUser()))
                .regionCode(social.getRegionCode())
                .dongCode(social.getDongCode())
                .dongName(social.getDongName())
                .likes(social.getLikes())
                .categoryName(categoryToCategoryName(social.getCategory()))
                .socialTags(social.getSocialTags())
                .status(social.getStatus())
                .title(social.getTitle())
                .startDate(social.getStartDate())
                .endDate(social.getEndDate())
                .limitedNums(social.getLimitedNums())
                .build();
    }

    @Override
    public List<SocialResponseDTO> socials() {
        return repo.findAll().stream()
                .map(social -> SocialResponseDTO.builder()
                        .userId(userToUserId(social.getUser()))
                        .regionCode(social.getRegionCode())
                        .dongCode(social.getDongCode())
                        .dongName(social.getDongName())
                        .likes(social.getLikes())
                        .categoryName(categoryToCategoryName(social.getCategory()))
                        .socialTags(social.getSocialTags())
                        .status(social.getStatus())
                        .title(social.getTitle())
                        .startDate(social.getStartDate())
                        .endDate(social.getEndDate())
                        .limitedNums(social.getLimitedNums())
                        .build())
                .collect(Collectors.toList());
    }

    public Long userToUserId(User user) {
        return user.getId();
    }

    public String categoryToCategoryName(Category category) {
        return category.getName();
    }
}
