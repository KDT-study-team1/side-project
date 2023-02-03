package com.sideproject.sideproject.feed.service;

import com.sideproject.sideproject.comment.exception.GlobalException;
import com.sideproject.sideproject.comment.exception.GlobalExceptionType;
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

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{

    private final FeedRepository feedRepository;

    private final SocialRepository socialRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String createFeed(FeedRequest feedRequest, String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            Social social = socialRepository.findById(feedRequest.getSocialId()).orElse(null);
            feedRepository.save(feedRequest.toEntity(user,social));
            return "success";
        } catch (Exception e){
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    public String deleteFeed(Long postId, String email) {

        return null;
    }

    @Override
    public String updateFeed(Long postId) {
        return null;
    }

    @Override
    public List<FeedResponse> selectFeeds() {
        return null;
    }

    @Override
    public FeedResponse selectFeed(Long PostId) {
        return null;
    }

    @Override
    public List<FeedResponse> userFeeds() {
        return null;
    }
}
