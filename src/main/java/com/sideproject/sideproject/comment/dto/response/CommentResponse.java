package com.sideproject.sideproject.comment.dto.response;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.CommentUserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "댓글 조회시 출력할 DTO")
public class CommentResponse {
    Long id;
    String content;
    LocalDateTime createDate;
    Long parentCommentId;

    Boolean deleted;

    CommentUserDTO commentUserDTO;
    Set<CommentResponse> childComments;

    public static CommentResponse from(CommentDTO dto) {
        return CommentResponse.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .parentCommentId(dto.getParentCommentId())
                .deleted(dto.getDeleted())
                .commentUserDTO(dto.getCommentUserDTO())
                .childComments(new TreeSet<>(Comparator.comparing(CommentResponse::getCreateDate)
                        .thenComparingLong(CommentResponse::getId)))
                .build();
    }


    public static Set<CommentResponse> organizeChildComments(
            Set<CommentDTO> dtos
    ) {
        Map<Long, CommentResponse> map = dtos.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toMap(CommentResponse::getId, Function.identity()));

        map.values().stream()
                .filter(CommentResponse::hasParentComment)
                .forEach(comment -> {
                    CommentResponse parentComment = map.get(comment.getParentCommentId());
                    parentComment.childComments.add(comment);
                });

        return map.values().stream()
                .filter(comment -> !comment.hasParentComment())
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(CommentResponse::getCreateDate)
                                .thenComparingLong(CommentResponse::getId))

                ));
    }

    private boolean hasParentComment() {
        return parentCommentId != null;
    }
}
