package com.sideproject.sideproject.comment.service;

import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;

import java.util.List;

public class CommentServiceImpl implements CommentService{
    @Override
    public List<CommentResponse> selectComments(Long postId) {
        return null;
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
