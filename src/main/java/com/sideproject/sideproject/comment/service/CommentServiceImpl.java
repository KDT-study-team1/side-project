package com.sideproject.sideproject.comment.service;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponse> selectComments(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(CommentDTO::from)
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public String saveComment(CommentRequest request) {
        return null;
    }

    @Override
    public String deleteComment(Long commentId, Long userId) {
        return null;
    }

    @Override
    public String updateComment(Long commentId, Long userId) {
        return null;
    }
}
