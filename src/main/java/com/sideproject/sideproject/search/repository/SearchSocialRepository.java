package com.sideproject.sideproject.search.repository;

import com.sideproject.sideproject.social.domain.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchSocialRepository extends JpaRepository<Social, Long> {

    @Query("SELECT s FROM Social s WHERE s.id IN " +
            "(SELECT st.social.id from SocialTag st where st.tag.id = :tagNum )")
    List<Social> findByTagId(@Param("tagNum") Long tagNum);

    List<Social> findByTitleContainingOrContentsContaining(String title, String content);

}
