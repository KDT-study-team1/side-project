package com.sideproject.sideproject.feed.domain;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.post.domain.PostImage;
import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "FEED")
@DiscriminatorValue("F")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends Post {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private Social social; //작성자가 참여 중인 모임 연결

    @Transient
    private boolean liked;

    @Builder
    public Feed(User user, List<PostImage> images, List<Comment> comments, String contents,
                Integer regionCode, Long dongCode, String dongName, int likes, Social social) {
        super(user, images, comments, contents, regionCode, dongCode, dongName, likes);
        this.social = social;
    }
}
