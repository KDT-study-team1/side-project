package com.sideproject.sideproject.post.domain;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.user.domain.User;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "POST")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "post_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class Post extends TimeAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user; //작성자

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @BatchSize(size = 100)
    protected List<PostImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @BatchSize(size = 100)
    private List<Comment> comments = new ArrayList<>();

    @NotNull
    protected String contents; //내용

    @NotNull
    @Column(name = "region_code")
    protected Integer regionCode; //시군구 5자리

    @NotNull
    @Column(name = "dong_code")
    protected Long dongCode; //읍면동 (자릿수 유동적)

    @NotNull
    @Column(name = "region_name")
    protected String dongName; //행정구역명

    @NotNull
    private int likes; //좋아요 수

    protected Post(User user, List<PostImage> images, List<Comment> comments, String contents, Integer regionCode,
                   Long dongCode, String dongName, int likes) {
        this.user = user;
        this.images = images;
        this.comments = comments;
        this.contents = contents;
        this.regionCode = regionCode;
        this.dongCode = dongCode;
        this.dongName = dongName;
        this.likes = likes;
    }

    public void updateLikes(int likes){
        this.likes = likes;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.images = postImages;
        postImages.forEach(postImage -> postImage.setPost(this));
    }
}
