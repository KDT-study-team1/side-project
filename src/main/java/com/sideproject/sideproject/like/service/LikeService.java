package com.sideproject.sideproject.like.service;

import com.sideproject.sideproject.like.domain.Like;
import com.sideproject.sideproject.like.repository.LikeRepository;
import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.post.exception.PostException;
import com.sideproject.sideproject.post.exception.PostExceptionType;
import com.sideproject.sideproject.post.repository.PostRepository;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.user.exception.UserException;
import com.sideproject.sideproject.user.exception.UserExceptionType;
import com.sideproject.sideproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public boolean likeButton(Long userId, Long postId) {

        //우선 사용자가 해당 포스트에 좋아요를 남겼는지 확인
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(UserExceptionType.NON_EXISTENT_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NON_EXISTENT_POST));
        boolean isLiked = likeRepository.existsByUserIdAndPostId(user.getId(), post.getId());

        //좋아요를 누른적이 없으면 like 하나 만들고 likes 하나 올리기
        //이미 좋아요가 있으면 그 좋아요를 삭제하고 likes 하나 줄이기
        if (!isLiked) {
            Like like = Like.createLike(user, post);
            likeRepository.save(like);
            post.updateLikes(post.getLikes() + 1);
            return true;
        } else {
            likeRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
            post.updateLikes(post.getLikes() - 1);
            return false;
        }
    }

    public Integer displayLikes(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NON_EXISTENT_POST));

        return post.getLikes();
    }

    public List<UserResponseDTO> displayLikedUsers(Long postId) {
        return likeRepository.findAllByPostId(postId)
                .stream()
                .map(Like::getUser)
                .map(UserResponseDTO::new)
                //일단은 USerResponseDTO로 반환하고
                //나중에 email이나 id만 반환하는 DTO를 새로 만드는 방식도 고려
                .collect(toList());
    }
}
