package com.sideproject.sideproject.social.domain;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.customer.domain.User;
import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.post.domain.PostImage;
import com.sideproject.sideproject.tag.domain.Category;
import com.sideproject.sideproject.tag.domain.SocialTag;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "SOCIAL")
@DiscriminatorValue("S")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Social extends Post {

    @OneToMany(mappedBy = "social", cascade = CascadeType.ALL)
    private List<Socialing> socialings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "social", cascade = CascadeType.ALL)
    private List<SocialTag> socialTags = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private SocialStatus status;

    @NotNull
    private String title; //모임 제목

    @NotNull
    private Integer hits; //조회수

    @NotNull
    @Column(name = "start_date")
    private LocalDateTime startDate; //모임 모집 시작 날짜
    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate; //모임 모집 마감 날짜

    @NotNull
    @Column(name = "current_nums")
    private Integer currentNums; //현재 신청한 인원수
    @NotNull
    @Column(name = "limited_nums")
    private Integer limitedNums; //최대 모집 인원수

    @Column(name = "contact")
    private String contact; //연락 방법

    @Builder
    public Social(User user, List<PostImage> images, List<Comment> comments, String contents,
                  Integer regionCode, Long dongCode, String dongName, int likes, List<Socialing> socialings,
                  Category category, List<SocialTag> socialTags, SocialStatus status, String title, Integer hits,
                  LocalDateTime startDate, LocalDateTime endDate, Integer currentNums, Integer limitedNums, String contact) {
        super(user, images, comments, contents, regionCode, dongCode, dongName, likes);
        this.socialings = socialings;
        this.category = category;
        this.socialTags = socialTags;
        this.status = status;
        this.title = title;
        this.hits = hits;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentNums = currentNums;
        this.limitedNums = limitedNums;
        this.contact = contact;
    }
}
