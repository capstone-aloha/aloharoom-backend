package com.aloharoombackend.repository;

import com.aloharoombackend.model.CommunityBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {
    List<CommunityBoard> findByTitleContaining(String keyword);
    List<CommunityBoard> findAllByUserId(Long userId);
}
