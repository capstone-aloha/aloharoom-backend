package com.aloharoombackend.service;

import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.dto.CommunityEditDto;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.model.CommunityImage;
import com.aloharoombackend.repository.CommunityBoardRepository;
import com.aloharoombackend.repository.CommunitySearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityBoardService{
    private final CommunityBoardRepository communityBoardRepository;
    private final CommunitySearchRepository communitySearchRepository;

    @Transactional
    public CommunityBoard create(CommunityBoard communityBoard) {
        return communityBoardRepository.save(communityBoard);
    }

    public CommunityBoard findOne(Long communityId) {
        return communityBoardRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 않습니다."));
    }

    public List<CommunityBoard> findAll() {
        List<CommunityBoard> communityBoards = communityBoardRepository.findAll();
        communityBoards.stream().forEach(communityBoard -> {
            communityBoard.getCommunityImages().stream()
                    .forEach(communityImage -> communityImage.getId());
        });
        return communityBoards;
    }

    public CommunityBoard findOneFetch(Long id) { // 프록시->실객체 생성
        CommunityBoard findCommunityId = communityBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 않습니다."));
        findCommunityId.getCommunityImages().stream().
                forEach(CommunityImage::getImgUrl);
        return findCommunityId;
    }


    @Transactional
    public CommunityBoard update(Long communityId, CommunityEditDto communityEditDto, List<CommunityImage> communityImages) {
        CommunityBoard communityBoard = communityBoardRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 않습니다."));
        return communityBoard.change(communityEditDto, communityImages);
    }

    @Transactional
    public void delete(CommunityBoard communityBoard) {
        communityBoardRepository.delete(communityBoard);
    }

    public List<CommunityBoard> searchCommunity(String keyword) {
        List<CommunityBoard> communityBoards = communityBoardRepository.findByTitleContaining(keyword);
        communityBoards.stream().forEach(communityBoard -> {
            communityBoard.getCommunityImages().stream()
                    .forEach(communityImage -> communityImage.getId());
        });
        return communityBoards;
    }

    public List<CommunityBoard> searchCommunityByNickName(String nickname) {
        List<CommunityBoard> communityBoards = communitySearchRepository.searchByNickName(nickname);
        communityBoards.stream().forEach(communityBoard -> {
            communityBoard.getCommunityImages().stream()
                    .forEach(communityImage -> communityImage.getId());
        });
        return communityBoards;
    }

    //내가 쓴 커뮤니티 조회
    public List<CommunityAllDto> getMyCommunity(Long userId) {
        List<CommunityBoard> communityBoards = communityBoardRepository.findAllByUserId(userId);
        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (int i = 0; i < communityBoards.size(); i++) {
            communityAllDtos.add(new CommunityAllDto(communityBoards.get(i)));
        }
        return communityAllDtos;
    }
}
