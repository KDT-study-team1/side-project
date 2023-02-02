package com.sideproject.sideproject.like.repository;

import com.sideproject.sideproject.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    /*
    이 두개는 나중에 필요할때를 대비하여 일단 만들어 놓음
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByPostId(Long postId);
    */

    @Query("SELECT l FROM Like l join fetch l.user u where l.post.id = :postId")
        //db에 없는 postId일때의 예외처리 필요할듯?
    List<Like> findAllByPostId(Long postId);

    @Modifying
    void deleteByUserIdAndPostId(Long userId, Long postId);

    Boolean existsByUserIdAndPostId(Long userId, Long postId);
}
