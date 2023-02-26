package com.aloharoombackend.repository;

import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeRepository extends JpaRepository<Home, Long> {

    @Override
    @EntityGraph(attributePaths = {"homeImages"})
    List<Home> findAll();

    @Override
    @EntityGraph(attributePaths = {"homeImages"})
    Optional<Home> findById(Long id);
}
