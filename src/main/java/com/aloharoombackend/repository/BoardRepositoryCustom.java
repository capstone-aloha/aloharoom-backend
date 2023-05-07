package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.model.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findAll();
    List<BoardAllDto> searchFilter(SearchFilterDto searchFilterDto);
    List<HeartBoardDto> recentViewBoard(List<Long> userIds);
}
