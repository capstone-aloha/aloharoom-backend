package com.aloharoombackend.repository;

import com.aloharoombackend.model.Heart;
import com.aloharoombackend.model.HeartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, HeartId>, HeartRepositoryCustom {
    void deleteByBoardId(Long boardId);
}
