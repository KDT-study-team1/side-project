package com.sideproject.sideproject.social.dto;

import com.sideproject.sideproject.social.domain.SocialStatus;
import com.sideproject.sideproject.tag.domain.SocialTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Schema(description = "모임 게시글 응답 DTO")
public class SocialResponseDTO {

    private Long userId;

    private Integer regionCode;

    private Long dongCode;

    private String dongName;

    private int likes;

    private String categoryName;

    private List<SocialTag> socialTags = new ArrayList<>();

    private SocialStatus status;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer limitedNums;

    @Builder
    public SocialResponseDTO(Long userId, Integer regionCode, Long dongCode, String dongName, int likes,
                             String categoryName, List<SocialTag> socialTags, SocialStatus status, String title,
                             LocalDateTime startDate, LocalDateTime endDate, Integer limitedNums) {
        this.userId = userId;
        this.regionCode = regionCode;
        this.dongCode = dongCode;
        this.dongName = dongName;
        this.likes = likes;
        this.categoryName = categoryName;
        this.socialTags = socialTags;
        this.status = status;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limitedNums = limitedNums;
    }
}
