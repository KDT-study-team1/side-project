package com.sideproject.sideproject.comment.service;

import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    //댓글 조회
    List<CommentResponse> selectComments(Long postId);

    // 댓글 작성
    String saveComment(CommentRequest request);

    //댓글 삭제
    String deleteComment(Long commentId, Long userId);

    //댓글 수정
    String updateComment(Long commentId, Long userId);


}
