package com.sideproject.sideproject.comment.controller;

import com.sideproject.sideproject.comment.dto.UserDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.service.CommentService;
import com.sideproject.sideproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public List<CommentResponse> selectComments(@PathVariable Long postId){
        List<CommentResponse> commentResponse = commentService.selectComments(postId);
        return commentResponse;
    }

    @PostMapping("")
    public ResponseDTO<?> saveComment(@RequestBody CommentRequest commentRequest){
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .nickname("gkgk")
                .profile("dd")
                .dongName("d동")
                .build(); //로그인 구현전 임시
        return new ResponseDTO<>(200,commentService.saveComment(commentRequest.toDTO(userDTO))
        ,"댓글 달기 성공");
    }

//    @DeleteMapping("/{commendId}")
//    public String deleteComment(@PathVariable Long commentId){
//        return commentService.deleteComment(commentId);
//    }
}
