package com.aloharoombackend.repository;

import com.aloharoombackend.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"board", "communityBoard"})
    List<Comment> findAllByBoardId(Long boardId);
    @EntityGraph(attributePaths = {"user", "board", "communityBoard"})
    List<Comment> findAllByCommunityBoardId(Long boardId);

    @Override
    @EntityGraph(attributePaths = {"user", "board"})
    Optional<Comment> findById(Long id);

    @EntityGraph(attributePaths = {"user", "board"})
    List<Comment> findAllByGroupId(Long groupId);

    @EntityGraph(attributePaths = {"board", "communityBoard"})
    List<Comment> findAllByUserId(Long userId);

    void deleteByBoardId(Long boardId);
    void deleteByCommunityBoardId(Long communityBoardId);
}
