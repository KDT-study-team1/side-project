package com.sideproject.sideproject.feed.repository;


import com.sideproject.sideproject.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}
