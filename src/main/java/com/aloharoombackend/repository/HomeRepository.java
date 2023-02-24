package com.aloharoombackend.repository;

import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {

    @Override
    @EntityGraph(attributePaths = {"homeImages"})
    List<Home> findAll();
}
