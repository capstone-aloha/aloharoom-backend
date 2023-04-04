package com.aloharoombackend.repository;

import com.aloharoombackend.model.HomeComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeCommentRepository extends JpaRepository<HomeComment, Long> {
    @EntityGraph(attributePaths = {"user", "board"})
    List<HomeComment> findAllByBoardId(Long boardId);

    @Override
    @EntityGraph(attributePaths = {"user", "board"})
    Optional<HomeComment> findById(Long id);
}
