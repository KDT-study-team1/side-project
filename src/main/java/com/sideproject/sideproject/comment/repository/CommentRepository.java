package com.sideproject.sideproject.comment.repository;

import com.sideproject.sideproject.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_Id(Long postId);
    int deleteByIdAndUser_Id(Long commentId, Long userId);

    Optional<Comment> findByIdAndUser_Id(Long id, Long userId);

    List<Comment> findByUser_Id(Long userId);
}
