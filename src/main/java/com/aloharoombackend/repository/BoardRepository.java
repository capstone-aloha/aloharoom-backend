package com.aloharoombackend.repository;

import com.aloharoombackend.model.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @Override
    @EntityGraph(attributePaths = {"home"})
    Optional<Board> findById(Long boardId);

    @Override
    @EntityGraph(attributePaths = {"user"})
    List<Board> findAll();

    List<Board> findAllByUserId(Long userId);
}
