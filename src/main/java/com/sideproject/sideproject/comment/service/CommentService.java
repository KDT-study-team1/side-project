package com.sideproject.sideproject.comment.service;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;

import java.util.List;
import java.util.Set;

public interface CommentService {

    //댓글 조회
    Set<CommentResponse> selectComments(Long postId);

    // 댓글 작성
    String saveComment(CommentDTO dto);

    //댓글 삭제
    String deleteComment(Long commentId, Long userId);

    //댓글 수정
    String updateComment(Long commentId, Long userId, CommentRequest commentRequest);

    //유저별 댓글 조회
    List<CommentResponse> userComment(Long userId);


}
