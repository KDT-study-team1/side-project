package com.sideproject.sideproject.social.dto;

import com.sideproject.sideproject.post.domain.PostImage;
import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.tag.domain.Category;
import com.sideproject.sideproject.tag.domain.SocialTag;
import com.sideproject.sideproject.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "모임 게시글 생성시 입력받는 DTO")
@Getter
@Setter
public class SocialRequestDTO {

    @Schema(description = "모임 게시글 이미지")
    private List<PostImageDTO> images;

    @Schema(description = "모임 게시글 내용", defaultValue = "집가고 싶다")
    private String contents;

    @Schema(description = "지역 코드", defaultValue = "12345")
    private Integer regionCode;

    @Schema(description = "행정 구역 코드", defaultValue = "055")
    private Long dongCode;

    @Schema(description = "동 이름", defaultValue = "우리집")
    private String dongName;

    @Schema(description = "카테고리")
    private Category category;

    @Schema(description = "소셜 태그", defaultValue = "null")
    private List<SocialTag> socialTags = new ArrayList<>();

    @Schema(description = "모임 게시글 제목", defaultValue = "같이 놀사람")
    private String title;

    @Schema(description = "시작 날짜", defaultValue = "yyyy-MM-dd")
    private LocalDateTime startDate;

    @Schema(description = "종료 날짜", defaultValue = "yyyy-MM-dd")
    private LocalDateTime endDate;

    @Schema(description = "모임 최대 참가수", defaultValue = "5")
    private Integer limitedNums;

    @Schema(description = "연락수단", defaultValue = "010-1234-5678")
    private String contact;

    public Social toEntity(User user) {
        return Social.builder()
                .user(user)
                .images(this.images.stream().map(image -> new PostImage(image.getImagePath())).collect(Collectors.toList()))
                .contents(this.contents)
                .regionCode(this.regionCode)
                .dongCode(this.dongCode)
                .dongName(this.dongName)
                .category(this.category)
                .socialTags(this.socialTags)
                .title(this.title)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .limitedNums(this.limitedNums)
                .contact(this.contact)
                .build();
    }
}
