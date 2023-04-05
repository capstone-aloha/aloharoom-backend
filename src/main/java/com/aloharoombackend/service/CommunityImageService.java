package com.aloharoombackend.service;

import com.aloharoombackend.model.CommunityImage;
import com.aloharoombackend.repository.CommunityImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityImageService {
    public final CommunityImageRepository communityImageRepository;

    @Transactional
    public void create(List<CommunityImage> communityImages) {
        communityImages.stream().forEach(communityImage -> communityImageRepository.save(communityImage));
    }
}
