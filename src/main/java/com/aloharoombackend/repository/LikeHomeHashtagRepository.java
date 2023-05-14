package com.aloharoombackend.repository;

import com.aloharoombackend.model.LikeHomeHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeHomeHashtagRepository extends JpaRepository<LikeHomeHashtag, Long> {

}
