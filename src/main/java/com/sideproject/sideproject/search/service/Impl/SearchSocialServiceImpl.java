package com.sideproject.sideproject.search.service.Impl;

import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.search.dto.SearchSocialResponseDTO;
import com.sideproject.sideproject.search.repository.SearchSocialRepository;
import com.sideproject.sideproject.search.service.SearchSocialService;
import com.sideproject.sideproject.social.domain.Social;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SearchSocialServiceImpl implements SearchSocialService {

    private final SearchSocialRepository repository;

    @Override
    @Transactional
    public List<SearchSocialResponseDTO> selectSocialByTag(Long tagNum) {

        return SearchSocialResponseDTO
                .SearchSocialResponseDTO(repository.findByTagId(tagNum));

    }

    @Override
    @Transactional
    public List<SearchSocialResponseDTO> selectSocialByKeyword(String keyword) {
        List<String> keywords = Stream.of(keyword.split("\\s"))
                .collect(Collectors.toList());

        List<Social> socials = new ArrayList<>();
        for (String word : keywords) {
            socials.addAll(repository.findByTitleContainingOrContentsContaining(word, word));
        }
        socials = socials.stream().filter(deduplication(Post::getId)).collect(Collectors.toList());

        return SearchSocialResponseDTO.SearchSocialResponseDTO(socials);
    }

    private static <T> Predicate<T> deduplication(Function<? super T, ?> key) {
        final Set<Object> set = ConcurrentHashMap.newKeySet();

        return predicate -> set.add(key.apply(predicate));
    }

}
