package com.sideproject.sideproject.comment.controller;

import com.sideproject.sideproject.comment.dto.CommentUserDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.service.CommentService;
import com.sideproject.sideproject.global.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Tag(name = "comment", description = "댓글 API")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 댓글 조회", description = "댓글 조회 api")
    public ResponseDTO<Set<CommentResponse>> selectComments(@PathVariable Long postId) {
        Set<CommentResponse> commentResponse = commentService.selectComments(postId);
        return new ResponseDTO<>(commentResponse);
    }

    @PostMapping("")
    @Operation(summary = "댓글 작성", description = "댓글 저장 api")
    public ResponseDTO<?> saveComment(@RequestBody @Valid CommentRequest commentRequest) {
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
    @Operation(summary = "댓글 삭제", description = "댓글 삭제 api")
    public ResponseDTO<?> deleteComment(@PathVariable Long commentId) {
        Long userId = 1L; //로그인 구현전 임시
        commentService.deleteComment(commentId, userId);
        return ResponseDTO.empty();
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "댓글 수정 api")
    public ResponseDTO<?> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequest commentRequest) {
        Long userId = 1L; //로그인 구현전 임시
        commentService.updateComment(commentId, userId, commentRequest);
        return ResponseDTO.empty();
    }

    @GetMapping("/me")
    @Operation(summary = "내 댓글 조회", description = "내 댓글 조회 api")
    public ResponseDTO<?> userComment() {
        Long userId = 1L; //로그인 구현전 임시
        List<CommentResponse> response = commentService.userComment(userId);
        return new ResponseDTO<>(response);
    }

}
