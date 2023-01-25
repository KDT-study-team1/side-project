package com.sideproject.sideproject.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "모임 게시글 이미지 DTO")
@Getter
public class PostImageDTO {

    @Schema(description = "이미지 경로", defaultValue = "1a2b3c4d.jpg")
    private String imagePath;

}
