package com.sideproject.sideproject.feed.controller;

import com.sideproject.sideproject.feed.dto.request.FeedRequest;
import com.sideproject.sideproject.feed.service.FeedServiceImpl;
import com.sideproject.sideproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
