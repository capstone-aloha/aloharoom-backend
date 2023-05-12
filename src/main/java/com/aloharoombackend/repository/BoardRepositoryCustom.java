package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.dto.RangeDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.model.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findAllByRange(RangeDto rangeDto);
    List<BoardAllDto> searchFilter(RangeDto rangeDto, SearchFilterDto searchFilterDto);
    List<HeartBoardDto> recentViewBoard(List<Long> userIds);
}
