package com.sideproject.sideproject.feed.domain;

import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.social.domain.Social;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
