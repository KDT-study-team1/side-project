package com.sideproject.sideproject.social.dto;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.post.domain.PostImage;
import com.sideproject.sideproject.social.domain.SocialStatus;
import com.sideproject.sideproject.tag.domain.Category;
import com.sideproject.sideproject.tag.domain.SocialTag;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SocialResponseDTO {

    private User user;

    private List<PostImage> images = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    private String contents;

    private Integer regionCode;

    private Long dongCode;

    private String dongName;

    private int likes;

    private Category category;

    private List<SocialTag> socialTags = new ArrayList<>();

    private SocialStatus status;

    private String title;

    private Integer hits;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer limitedNums;

    private String contact;

    @Builder

    public SocialResponseDTO(User user, List<PostImage> images, List<Comment> comments, String contents,
                             Integer regionCode, Long dongCode, String dongName, int likes, Category category,
                             List<SocialTag> socialTags, SocialStatus status, String title, Integer hits,
                             LocalDateTime startDate, LocalDateTime endDate, Integer limitedNums, String contact) {
        this.user = user;
        this.images = images;
        this.comments = comments;
        this.contents = contents;
        this.regionCode = regionCode;
        this.dongCode = dongCode;
        this.dongName = dongName;
        this.likes = likes;
        this.category = category;
        this.socialTags = socialTags;
        this.status = status;
        this.title = title;
        this.hits = hits;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limitedNums = limitedNums;
        this.contact = contact;
    }
}
