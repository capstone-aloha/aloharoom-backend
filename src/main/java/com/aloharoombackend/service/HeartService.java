package com.aloharoombackend.service;

import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.model.Heart;
import com.aloharoombackend.model.HeartId;
import com.aloharoombackend.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    @Transactional
    public String save(Long boardId, Long loginUserId) {
        heartRepository.save(new Heart(boardId, loginUserId));
        return "좋아요 추가 완료";
    }

    public Heart findById(HeartId heartId) {
        return heartRepository.findById(heartId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 좋아요가 존재하지 않습니다."));
    }

    @Transactional
    public String delete(Long boardId, Long loginUserId) {
        Heart heart = findById(new HeartId(boardId, loginUserId));
        heartRepository.delete(heart);
        return "좋아요 삭제 완료";
    }

    @Transactional
    public void deleteByBoardId(Long boardId) {
        heartRepository.deleteByBoardId(boardId);
    }

    public List<HeartBoardDto> findByHeartBoard(Long loginUserId) {
        return heartRepository.findByHeartBoard(loginUserId);
    }

    public Boolean findByBoardIdAndUserId(Long boardId, Long loginUserId) {
        if(heartRepository.findByBoardIdAndUserId(boardId, loginUserId) == null)
            return false;
        return true;
    }
}
