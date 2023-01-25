package com.sideproject.sideproject.comment.service;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.exception.GlobalException;
import com.sideproject.sideproject.comment.exception.GlobalExceptionType;
import com.sideproject.sideproject.comment.repository.CommentRepository;
import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.post.repository.PostRepository;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public Set<CommentResponse> selectComments(Long postId) {
        Set<CommentDTO> dtos = commentRepository.findByPost_Id(postId)
                .stream()
                .map(CommentDTO::from)
                .collect(Collectors.toSet());
        return CommentResponse.organizeChildComments(dtos);

    }

    @Override
    @Transactional
    public String saveComment(CommentDTO dto) {
        try {
            Post post = postRepository.findById(dto.getPostId()).orElseThrow();
            User user = userRepository.findById(dto.getCommentUserDTO().getId()).orElseThrow();

            Comment comment = dto.toEntity(user, post, dto.getContent(), dto.getParentCommentId());
            commentRepository.save(comment);
            return "success";

        } catch (Exception e) {
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    @Transactional
    public String deleteComment(Long commentId, Long userId) {
        try {
            Comment comment = commentRepository.findByIdAndUser_Id(commentId, userId).orElseThrow(Exception::new);
            comment.delete();
            return "success";
        } catch (Exception e) {
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    @Transactional
    public String updateComment(Long commentId, Long userId, CommentRequest request) {
        try {
            Comment comment = commentRepository.findByIdAndUser_Id(commentId, userId).orElseThrow(Exception::new);
            comment.update(request.getContent());
            return "success";
        } catch (Exception e) {
            throw new GlobalException(GlobalExceptionType.DATA_ACCESS_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> userComment(Long userId) {
        return commentRepository.findByUser_IdAndDeletedIsFalseOrderByCreateDateDesc(userId)
                .stream()
                .map(CommentDTO::from)
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}
