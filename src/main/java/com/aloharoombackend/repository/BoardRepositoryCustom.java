package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.SearchFilterDto;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardAllDto> searchFilter(SearchFilterDto searchFilterDto);
}
