package com.sideproject.sideproject.post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "IMAGE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; //연결된 글

    @Column(length = 1000)
    private String imagePath;

    public PostImage(String imagePath) {
        this.imagePath = imagePath;
    }

    //==연관관계 메서드==//
    public void setPost(Post post){
        this.post = post;
    }

}
