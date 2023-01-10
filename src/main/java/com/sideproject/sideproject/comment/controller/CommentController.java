package com.sideproject.sideproject.comment.controller;

import com.sideproject.sideproject.comment.dto.UserDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.service.CommentService;
import com.sideproject.sideproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseDTO<List<CommentResponse>> selectComments(@PathVariable Long postId) {
        List<CommentResponse> commentResponse = commentService.selectComments(postId);
        return new ResponseDTO<>(
                200,
                "success",
                "댓글 조회 성공",
                commentResponse
        );
    }

    @PostMapping("")
    public ResponseDTO<?> saveComment(@RequestBody CommentRequest commentRequest) {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .nickname("유저1")
                .profile("sjfewfe")
                .dongName("a동")
                .build(); //로그인 구현전 임시
        String result = commentService.saveComment(commentRequest.toDTO(userDTO));
        return new ResponseDTO<>(
                200,
                result,
                "댓글 달기 성공"
        );
    }

    @DeleteMapping("/{commentId}")
    public ResponseDTO<?> deleteComment(@PathVariable Long commentId) {
        Long userId = 1L; //로그인 구현전 임시
        String result = commentService.deleteComment(commentId, userId);
        return new ResponseDTO<>(
                200,
                result,
                "댓글 삭제 성공"
        );
    }

    @PutMapping("/{commentId}")
    public ResponseDTO<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        Long userId = 1L; //로그인 구현전 임시
        String result = commentService.updateComment(commentId, userId, commentRequest);
        return new ResponseDTO<>(
                200,
                result,
                "댓글 수정 성공"
        );
    }

    @GetMapping("/me")
    public ResponseDTO<?> userComment() {
        Long userId = 1L; //로그인 구현전 임시
        List<CommentResponse> response = commentService.userComment(userId);
        return new ResponseDTO<>(
                200,
                "success",
                "유저의 댓글 조회 성공",
                response
        );
    }

}
