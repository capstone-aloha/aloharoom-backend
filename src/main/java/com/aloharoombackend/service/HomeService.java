package com.aloharoombackend.service;

import com.aloharoombackend.dto.BoardEditDto;
import com.aloharoombackend.model.HomeImage;
import com.aloharoombackend.repository.HomeRepository;
import com.aloharoombackend.model.Home;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;

    @Transactional
    public Home create(Home home) {
        return homeRepository.save(home);
    }

    public Home findOne(Long homeId) {
        return homeRepository.findById(homeId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 집이 존재하지 않습니다."));
    }

    public List<Home> findAll() {
        return homeRepository.findAll();
    }

    @Transactional
    public Home update(Long homeId, BoardEditDto boardEditDto, List<HomeImage> homeImages) {
        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 집이 존재하지 않습니다."));
        return home.change(boardEditDto, homeImages);
    }
}
