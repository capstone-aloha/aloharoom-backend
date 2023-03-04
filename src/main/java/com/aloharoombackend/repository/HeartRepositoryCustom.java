package com.aloharoombackend.repository;

import com.aloharoombackend.dto.HeartBoardDto;

import java.util.List;

public interface HeartRepositoryCustom {
    List<HeartBoardDto> findByHeartBoard(Long userId);
}
