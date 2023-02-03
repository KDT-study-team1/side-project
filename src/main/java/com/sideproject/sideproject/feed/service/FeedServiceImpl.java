package com.sideproject.sideproject.feed.service;

import com.sideproject.sideproject.comment.exception.GlobalException;
import com.sideproject.sideproject.comment.exception.GlobalExceptionType;
import com.sideproject.sideproject.feed.domain.Feed;
import com.sideproject.sideproject.feed.dto.request.FeedRequest;
import com.sideproject.sideproject.feed.dto.response.FeedResponse;
import com.sideproject.sideproject.feed.repository.FeedRepository;
import com.sideproject.sideproject.social.domain.Social;
import com.sideproject.sideproject.social.repository.SocialRepository;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    private final SocialRepository socialRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String createFeed(FeedRequest feedRequest, String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            Social social = socialRepository.findById(feedRequest.getSocialId()).orElse(null);
            feedRepository.save(feedRequest.toEntity(user, social));
            return "success";
        } catch (Exception e) {
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    public String deleteFeed(Long postId, String email) {

        return null;
    }

    @Override
    @Transactional
    public String updateFeed(Long postId, String email, FeedRequest feedRequest) {
        try {
            Feed feed = feedRepository.findByIdAndUserEmail(postId, email).orElseThrow();
            feed.updateContents(feedRequest.getContent());
            return "success";
        } catch (Exception e) {
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedResponse> selectFeeds() {
        return feedRepository.findAll().stream()
                .map(FeedResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedResponse selectFeed(Long postId) {
        try {
            Feed feed = feedRepository.findById(postId).orElseThrow();
            return FeedResponse.from(feed);
        }catch (Exception e){
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedResponse> userFeeds(String email) {
        return feedRepository.findAllByUserEmail(email).stream()
                .map(FeedResponse::from)
                .collect(Collectors.toList());
    }
}
