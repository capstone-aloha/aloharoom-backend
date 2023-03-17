package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.dto.SearchFilterDto;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardAllDto> searchFilter(SearchFilterDto searchFilterDto);
    List<HeartBoardDto> recentViewBoard(List<Long> userIds);
}
