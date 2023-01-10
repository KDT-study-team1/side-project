package com.sideproject.sideproject.comment.service;

import com.sideproject.sideproject.comment.domain.Comment;
import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.request.CommentRequest;
import com.sideproject.sideproject.comment.dto.response.CommentResponse;
import com.sideproject.sideproject.comment.repository.CommentRepository;
import com.sideproject.sideproject.customer.domain.User;
import com.sideproject.sideproject.customer.rspository.UserRepository;
import com.sideproject.sideproject.post.domain.Post;
import com.sideproject.sideproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Override
    public List<CommentResponse> selectComments(Long postId) {
        return commentRepository.findByPost_Id(postId)
                .stream()
                .map(CommentDTO::from)
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public String saveComment(CommentDTO dto) {
        try {
            Post post = postRepository.findById(dto.getPostId()).orElse(null);
            User user = userRepository.findById(dto.getUserDTO().getId()).orElse(null);

            Comment comment = dto.toEntity(user,post, dto.getContent());

            commentRepository.save(comment);
        } catch (Exception e){
            return "failed";
        }
        return "success";
    }

    @Override
    @Transactional
    public String deleteComment(Long commentId, Long userId) {
        try {
            int result = commentRepository.deleteByIdAndUser_Id(commentId, userId);
            if (result==0){
                return "failed";
            }else{
                return "success";
            }
        }catch (Exception e){
            return "failed";
        }
    }

    @Override
    @Transactional
    public String updateComment(Long commentId, Long userId, CommentRequest request) {
        try {
            Comment comment = commentRepository.findByIdAndUser_Id(commentId, userId).orElseThrow(Exception::new);
            if (comment == null) {
                return "failed";
            }
            comment.update(request.getContent());
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    @Override
    public List<CommentResponse> userComment(Long userId) {
        return commentRepository.findByUser_Id(userId)
                .stream()
                .map(CommentDTO::from)
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}
