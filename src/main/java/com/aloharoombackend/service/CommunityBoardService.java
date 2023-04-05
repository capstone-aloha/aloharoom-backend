package com.aloharoombackend.service;

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

}
