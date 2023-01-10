package com.sideproject.sideproject.post.repository;

import com.sideproject.sideproject.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
