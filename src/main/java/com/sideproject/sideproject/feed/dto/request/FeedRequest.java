package com.sideproject.sideproject.feed.dto.request;

import com.sideproject.sideproject.feed.domain.Feed;
import com.sideproject.sideproject.feed.dto.FeedDTO;
import com.sideproject.sideproject.feed.dto.FeedSocialDTO;
import com.sideproject.sideproject.feed.dto.FeedUserDTO;
import com.sideproject.sideproject.post.domain.PostImage;
import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.user.domain.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FeedRequest {

    private List<String> images;

    private Long socialId;

    private String content;

    public Feed toEntity(User user, Social social) {
        Feed feed = Feed.builder()
                .user(user)
                .contents(content)
                .social(social)
                .dongCode(social.getDongCode())
                .dongName(social.getDongName())
                .regionCode(social.getRegionCode())
                .build();

        feed.setPostImages(images.stream().map(PostImage::new).collect(Collectors.toList()));
        return feed;
    }

    public FeedDTO toDto(FeedUserDTO feedUserDTO, FeedSocialDTO feedSocialDTO) {
        return FeedDTO.builder()
                .feedUserDTO(feedUserDTO)
                .feedSocialDTO(feedSocialDTO)
                .images(this.images)
                .content(content)
                .build();
    }

}
