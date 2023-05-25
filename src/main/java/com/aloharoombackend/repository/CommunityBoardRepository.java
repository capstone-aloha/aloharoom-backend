package com.aloharoombackend.repository;

import com.aloharoombackend.model.CommunityBoard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {

    @Override
    @EntityGraph(attributePaths = {"communityImages"})
    Optional<CommunityBoard> findById(Long id);
    @Override
    @EntityGraph(attributePaths = {"comments"})
    List<CommunityBoard> findAll();
    List<CommunityBoard> findByTitleContaining(String keyword);
    List<CommunityBoard> findByTitleContainingAndCode(String keyword, Integer code);
    List<CommunityBoard> findAllByUserId(Long userId);
}
