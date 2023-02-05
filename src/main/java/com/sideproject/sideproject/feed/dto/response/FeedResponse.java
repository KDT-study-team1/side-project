package com.sideproject.sideproject.feed.dto.response;

import com.sideproject.sideproject.feed.domain.Feed;
import com.sideproject.sideproject.feed.dto.FeedSocialDTO;
import com.sideproject.sideproject.feed.dto.FeedUserDTO;
import com.sideproject.sideproject.post.domain.PostImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FeedResponse {

    private Long id;
    private FeedUserDTO feedUserDTO;
    private FeedSocialDTO feedSocialDTO;
    private List<String> imagePath;
    private String content;
    private LocalDateTime createDate;
    private int likes;

    public static FeedResponse from(Feed feed) {
        return FeedResponse.builder()
                .id(feed.getId())
                .feedUserDTO(FeedUserDTO.from(feed.getUser()))
                .feedSocialDTO(FeedSocialDTO.from(feed.getSocial()))
                .imagePath(feed.getImages().stream()
                        .map(PostImage::getImagePath)
                        .collect(Collectors.toList()))
                .content(feed.getContents())
                .createDate(feed.getCreateDate())
                .likes(feed.getLikes())
                .build();
    }

}
