package com.aloharoombackend.service;

import com.aloharoombackend.model.HomeImage;
import com.aloharoombackend.repository.HomeImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeImageService {
    private final HomeImageRepository homeImageRepository;

    @Transactional
    public HomeImage create(HomeImage homeImage) {
        return homeImageRepository.save(homeImage);
    }

    @Transactional
    public void delete(HomeImage homeImage) {
        homeImageRepository.delete(homeImage);
    }
}
