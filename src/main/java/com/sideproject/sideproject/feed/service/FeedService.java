package com.sideproject.sideproject.feed.service;

import com.sideproject.sideproject.feed.dto.FeedDTO;
import com.sideproject.sideproject.feed.dto.response.FeedResponse;

import java.util.List;

public interface FeedService {

    String createFeed(FeedDTO feedDTO);

    String deleteFeed(Long postId);

    String updateFeed(Long postId);

    List<FeedResponse> selectFeeds();

    FeedResponse selectFeed(Long PostId);

    List<FeedResponse> userFeeds();

}
