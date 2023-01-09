package com.sideproject.sideproject.comment.domain;

import com.sideproject.sideproject.customer.domain.User;
import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.post.domain.TimeAuditingEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "COMMENT")
public class Comment extends TimeAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; // 댓글이 달린 글

    @NotNull
    private String content;

    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
    }

    public static Comment of(User user, Post post, String content){
        return new Comment(user, post, content);
    }

//    @Column(name = "c_group")
//    private int cGroup; // group
//    private int level; // 계층
//
//    @Column(name = "ref_order")
//    private int refOrder; // 같은 그룹 내의 순서
//
//    @Column(name = "parent_num")
//    private Long parentNum; // 부모 댓글의 ID
//    private Boolean deleted = false; //삭제되었는지 여부
//    private String regionName;
}
