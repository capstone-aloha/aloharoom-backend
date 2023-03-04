package com.aloharoombackend.service;

import com.aloharoombackend.model.Heart;
import com.aloharoombackend.model.HeartId;
import com.aloharoombackend.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    @Transactional
    public Heart save(Heart like) {
        return heartRepository.save(like);
    }

    public Heart findById(HeartId heartId) {
        return heartRepository.findById(heartId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 좋아요가 존재하지 않습니다."));
    }

    @Transactional
    public void delete(HeartId heartId) {
        Heart heart = findById(heartId);
        heartRepository.delete(heart);
    }
}
