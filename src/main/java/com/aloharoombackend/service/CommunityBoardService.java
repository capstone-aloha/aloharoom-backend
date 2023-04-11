package com.aloharoombackend.service;

import com.aloharoombackend.dto.CommunityEditDto;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.model.CommunityImage;
import com.aloharoombackend.repository.CommunityBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityBoardService{
    private final CommunityBoardRepository communityBoardRepository;

    @Transactional
    public CommunityBoard create(CommunityBoard communityBoard) {
        return communityBoardRepository.save(communityBoard);
    }

    public CommunityBoard findOne(Long communityId) {
        return communityBoardRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 안ㅅ습니다."));
    }

    public CommunityBoard findOneFetch(Long id) { // 프록시->실객체 생성
        CommunityBoard findCommunityId = communityBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 안ㅅ습니다."));
        findCommunityId.getCommunityImages().stream().
                forEach(communityImage -> communityImage.getImgUrl());
        return findCommunityId;
    }

    @Transactional
    public CommunityBoard update(Long communityId, CommunityEditDto communityEditDto, List<CommunityImage> communityImages) {
        CommunityBoard communityBoard = communityBoardRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 안ㅅ습니다."));
        return communityBoard.change(communityEditDto, communityImages);
    }
}
