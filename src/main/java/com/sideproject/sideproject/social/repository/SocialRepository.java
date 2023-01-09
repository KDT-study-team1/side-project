package com.sideproject.sideproject.social.repository;

import com.sideproject.sideproject.social.domain.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {
}
