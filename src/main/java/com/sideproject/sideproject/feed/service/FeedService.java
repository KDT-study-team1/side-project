package com.sideproject.sideproject.feed.service;

import com.sideproject.sideproject.feed.dto.request.FeedRequest;
import com.sideproject.sideproject.feed.dto.response.FeedResponse;

import java.util.List;

public interface FeedService {

    String createFeed(FeedRequest feedRequest, String email);

    String deleteFeed(Long postId, String email);

    String updateFeed(Long postId, String email, FeedRequest feedRequest);

    List<FeedResponse> selectFeeds();

    FeedResponse selectFeed(Long PostId);

    List<FeedResponse> userFeeds(String email);

}
