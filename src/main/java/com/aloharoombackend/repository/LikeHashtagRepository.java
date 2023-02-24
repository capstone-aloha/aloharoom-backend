package com.aloharoombackend.repository;

import com.aloharoombackend.model.LikeHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeHashtagRepository extends JpaRepository<LikeHashtag, Long> {
}
