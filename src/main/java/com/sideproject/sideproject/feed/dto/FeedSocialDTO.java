package com.sideproject.sideproject.feed.dto;

import com.sideproject.sideproject.social.domain.Social;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FeedSocialDTO {
    private Long id;
    private String dongName;
    private String title;
    private LocalDateTime createDate;

    public static FeedSocialDTO from(Social social){
        return FeedSocialDTO.builder()
                .id(social.getId())
                .dongName(social.getDongName())
                .title(social.getTitle())
                .createDate(social.getCreateDate())
                .build();
    }

}
