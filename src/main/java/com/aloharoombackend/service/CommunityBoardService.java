package com.aloharoombackend.service;

import com.aloharoombackend.dto.CommunityEditDto;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.repository.CommunityBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CommunityBoard update(Long communityId, CommunityEditDto communityEditDto) {
        CommunityBoard communityBoard = communityBoardRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 안ㅅ습니다."));
        return communityBoard.change(communityEditDto);
    }
}
