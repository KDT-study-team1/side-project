package com.sideproject.sideproject.feed.controller;

import com.sideproject.sideproject.feed.dto.request.FeedRequest;
import com.sideproject.sideproject.feed.dto.response.FeedResponse;
import com.sideproject.sideproject.feed.service.FeedServiceImpl;
import com.sideproject.sideproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedServiceImpl feedService;

    @PostMapping("")
    public ResponseDTO<?> createFeed(@RequestBody FeedRequest feedRequest) {
        String email = "a@naver.com";
        feedService.createFeed(feedRequest, email);
        return ResponseDTO.empty();
    }

    @GetMapping("")
    public ResponseDTO<List<FeedResponse>> selectFeeds(String filter) {
        List<FeedResponse> feedResponses = feedService.selectFeeds(filter);
        return new ResponseDTO<>(feedResponses);
    }

    @GetMapping("/{postId}")
    public ResponseDTO<?> selectFeed(@PathVariable Long postId) {
        FeedResponse feedResponse = feedService.selectFeed(postId);
        if (feedResponse == null) {
            return ResponseDTO.empty();
        }
        return new ResponseDTO<>(feedResponse);
    }

    @PutMapping("/{postId}")
    public ResponseDTO<?> updateFeed(@PathVariable Long postId, @RequestBody FeedRequest feedRequest) {
        String email = "a@naver.com";
        feedService.updateFeed(postId, email, feedRequest);
        return ResponseDTO.empty();
    }

    @GetMapping("/me")
    public ResponseDTO<?> userFeeds() {
        String email = "a@naver.com";
        List<FeedResponse> feedResponses = feedService.userFeeds(email);
        return new ResponseDTO<>(feedResponses);
    }

}
