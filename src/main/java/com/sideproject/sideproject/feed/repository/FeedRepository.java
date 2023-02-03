package com.sideproject.sideproject.feed.repository;


import com.sideproject.sideproject.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findByIdAndUserEmail(Long postId, String email);

    List<Feed> findAllByUserEmail(String email);
}
