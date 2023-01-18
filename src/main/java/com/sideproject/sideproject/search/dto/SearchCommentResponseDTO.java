package com.sideproject.sideproject.search.dto;

import com.sideproject.sideproject.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchCommentResponseDTO {
    private String id;

    private SearchUserResponseDTO searchUserResponseDTO;

    private SearchPostResponseDTO searchPostResponseDTO;

    private String content;


    @Builder
    public SearchCommentResponseDTO(Comment comment){
        this.searchUserResponseDTO = new SearchUserResponseDTO(comment.getUser());
        this.searchPostResponseDTO = new SearchPostResponseDTO(comment.getPost());
        this.id = String.valueOf(comment.getId());
        this.content = comment.getContent();
    }

    static public List<SearchCommentResponseDTO> SearchCommentResponseDTO(List<Comment> list){
        return new ArrayList<>(list.stream()
                .map(SearchCommentResponseDTO::new)
                .collect(Collectors.toList()));
    }
}
