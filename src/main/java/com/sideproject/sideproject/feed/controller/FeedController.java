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
    public ResponseDTO<?> createFeed(@RequestBody FeedRequest feedRequest){
        String email = "a@naver.com";
        feedService.createFeed(feedRequest,email);
        return ResponseDTO.empty();
    }

    @GetMapping("")
    public ResponseDTO<List<FeedResponse>> selectFeeds(String filter){
        List<FeedResponse> feedResponses = feedService.selectFeeds();
        return new ResponseDTO<>(feedResponses);
    }



}
