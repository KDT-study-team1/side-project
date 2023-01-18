package com.sideproject.sideproject.search.dto;

import com.sideproject.sideproject.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchPostResponseDTO {
    private String id;

    private String contents;

    private String regionCode;

    private String dongCode;

    private String dongName;

    private String likes;

    @Builder
    public SearchPostResponseDTO(String id, String contents, String regionCode, String dongCode, String dongName, String likes) {
        this.id = id;
        this.contents = contents;
        this.regionCode = regionCode;
        this.dongCode = dongCode;
        this.dongName = dongName;
        this.likes = likes;
    }
    public SearchPostResponseDTO(Post post) {
        this.id = String.valueOf(post.getId());
        this.contents = post.getContents();
        this.regionCode = String.valueOf(post.getRegionCode());
        this.dongCode = post.getDongName();
        this.dongName = post.getDongName();
        this.likes = String.valueOf(post.getLikes());
    }
}
