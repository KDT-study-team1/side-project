package com.sideproject.sideproject.comment.controller;

import com.sideproject.sideproject.comment.dto.CommentUserDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.service.CommentService;
import com.sideproject.sideproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseDTO<Set<CommentResponse>> selectComments(@PathVariable Long postId) {
        Set<CommentResponse> commentResponse = commentService.selectComments(postId);
        return new ResponseDTO<>(commentResponse);
    }

    @PostMapping("")
    public ResponseDTO<?> saveComment(@RequestBody CommentRequest commentRequest) {
        CommentUserDTO commentUserDTO = CommentUserDTO.builder()
                .id(1L)
                .nickname("유저1")
                .profile("sjfewfe")
                .dongName("a동")
                .build(); //로그인 구현전 임시
        commentService.saveComment(commentRequest.toDTO(commentUserDTO));
        return ResponseDTO.empty();
    }

    @DeleteMapping("/{commentId}")
    public ResponseDTO<?> deleteComment(@PathVariable Long commentId) {
        Long userId = 1L; //로그인 구현전 임시
        commentService.deleteComment(commentId, userId);
        return ResponseDTO.empty();
    }

    @PutMapping("/{commentId}")
    public ResponseDTO<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        Long userId = 1L; //로그인 구현전 임시
        commentService.updateComment(commentId, userId, commentRequest);
        return ResponseDTO.empty();
    }

    @GetMapping("/me")
    public ResponseDTO<?> userComment() {
        Long userId = 1L; //로그인 구현전 임시
        List<CommentResponse> response = commentService.userComment(userId);
        return new ResponseDTO<>(response);
    }

}
