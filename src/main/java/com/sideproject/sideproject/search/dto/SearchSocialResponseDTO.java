package com.sideproject.sideproject.search.dto;

import com.sideproject.sideproject.social.domain.Social;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchSocialResponseDTO {

    private String title;

    private String hits;

    private String startDate;

    private String endDate;

    private String currentNums;

    private String limitedNums;

    private String contact;

    private SearchPostResponseDTO searchPostResponseDTO;

    private SearchUserResponseDTO searchUserResponseDTO;

    private List<SearchCommentResponseDTO> searchCommentResponseDTO;

    @Builder
    public SearchSocialResponseDTO(Social social){
        searchUserResponseDTO = new SearchUserResponseDTO(social.getUser());
        searchPostResponseDTO = new SearchPostResponseDTO(String.valueOf(
                social.getId()),
                social.getContents(),
                String.valueOf(social.getRegionCode()),
                String.valueOf(social.getDongCode()),
                social.getDongName(),
                String.valueOf(social.getLikes())
        );
        searchCommentResponseDTO = SearchCommentResponseDTO.SearchCommentResponseDTO(social.getComments());

        this.title = social.getTitle();
        this.hits = String.valueOf(social.getHits());
        this.startDate = String.valueOf(social.getStartDate());
        this.endDate = String.valueOf(social.getEndDate());
        this.currentNums = String.valueOf(social.getCurrentNums());
        this.limitedNums = String.valueOf(social.getLimitedNums());
        this.contact = social.getContact();
    }

    static public List<SearchSocialResponseDTO> SearchSocialResponseDTO(List<Social> socialList){
        return new ArrayList<>(socialList.stream()
                .map(SearchSocialResponseDTO::new)
                .collect(Collectors.toList()));
    }
}
