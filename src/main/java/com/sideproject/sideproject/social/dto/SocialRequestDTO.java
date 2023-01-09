package com.sideproject.sideproject.social.dto;

import com.sideproject.sideproject.customer.domain.User;
import com.sideproject.sideproject.post.domain.PostImage;
import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.tag.domain.Category;
import com.sideproject.sideproject.tag.domain.SocialTag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SocialRequestDTO {

    private User user;

    protected List<PostImage> images = new ArrayList<>();

    protected String contents;

    protected Integer regionCode;

    protected Long dongCode;

    protected String dongName;

    private Category category;

    private List<SocialTag> socialTags = new ArrayList<>();

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer limitedNums;

    private String contact;

    public Social toEntity() {
        return Social.builder()
                .user(this.user)
                .images(this.images)
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
