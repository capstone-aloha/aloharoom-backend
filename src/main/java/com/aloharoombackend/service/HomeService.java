package com.aloharoombackend.service;

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

    public List<Home> findAll() {
        return homeRepository.findAll();
    }
}
