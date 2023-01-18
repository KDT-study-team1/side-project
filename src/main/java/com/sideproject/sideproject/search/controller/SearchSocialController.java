package com.sideproject.sideproject.search.controller;

import com.sideproject.sideproject.global.response.ErrorResponseDTO;
import com.sideproject.sideproject.global.response.ResponseDTO;
import com.sideproject.sideproject.search.dto.SearchSocialResponseDTO;
import com.sideproject.sideproject.search.service.Impl.SearchSocialServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "search", description = "검색 API")
public class SearchSocialController {

    private final SearchSocialServiceImpl service;

    @GetMapping("/social/search/{tagNum}")
    @Operation(summary = "태그 검색 기능", description = "모임 게시글 태그 검색 api")
    public ResponseDTO<?> doSearchSocialByTag(@PathVariable Long tagNum) {
        List<SearchSocialResponseDTO> list = service.selectSocialByTag(tagNum);

        ResponseDTO<?> responseDTO;
        try {
            if (Objects.isNull(list) || list.isEmpty()) {
                ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO(500, "응답 성공같은 실패?");
                responseDTO = new ResponseDTO<>(errorResponseDTO);
            } else {
                responseDTO = new ResponseDTO<>(list);
            }
        } catch (Exception e) {
            return null;
        }

        return responseDTO;

    }
}
