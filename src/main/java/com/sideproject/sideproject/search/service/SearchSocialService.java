package com.sideproject.sideproject.search.service;

import com.sideproject.sideproject.search.dto.SearchSocialResponseDTO;

import java.util.List;

public interface SearchSocialService {
    public List<SearchSocialResponseDTO> selectSocialByTag (Long tagId);
}
