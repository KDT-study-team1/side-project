package com.sideproject.sideproject.search.service.Impl;

import com.sideproject.sideproject.search.dto.SearchSocialResponseDTO;
import com.sideproject.sideproject.search.repository.SearchSocialRepository;
import com.sideproject.sideproject.search.service.SearchSocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchSocialServiceImpl implements SearchSocialService {

    private final SearchSocialRepository repository;

    @Override
    public List<SearchSocialResponseDTO> selectSocialByTag(Long tagNum) {

        return SearchSocialResponseDTO
                .SearchSocialResponseDTO(repository.findByTagId(tagNum));

    }
}
