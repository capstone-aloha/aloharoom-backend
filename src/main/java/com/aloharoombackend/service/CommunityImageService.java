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
        communityImageRepository.saveAll(communityImages);
    }

    public CommunityImage findOne(Long communityImageId) {
        return communityImageRepository.findById(communityImageId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티 이미지가 존재하지 않습니다."));
    }

    public void delete(CommunityImage communityImage) {
        communityImageRepository.delete(communityImage);
    }
}
