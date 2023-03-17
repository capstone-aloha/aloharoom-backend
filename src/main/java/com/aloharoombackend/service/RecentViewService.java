package com.aloharoombackend.service;

import com.aloharoombackend.model.RecentView;
import com.aloharoombackend.repository.RecentViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class RecentViewService {
    private final RecentViewRepository recentViewRepository;

    @Transactional
    public void create(RecentView recentView) {
        //최근 본 글 10개 제한 후 교체되도록 개발
        List<RecentView> recentViews = recentViewRepository.findByUserId(recentView.getUserId());
        boolean existView = recentViews.stream().anyMatch(recentView1 -> recentView1.getBoardId() == recentView.getBoardId());
        if(existView) { //최근 본 글에 존재할 때 (변경 감지)
            recentViews.stream().forEach(recentView1 -> {
                if(recentView1.getBoardId() == recentView.getBoardId()) {
                    recentView1.setCreatedDate(LocalDateTime.now());
                }
            });
        } else {
            if(recentViews.size()>=10)  { //최근 본 글이 10개 이상일 경우 제일 마지막에 본 글 삭제
                deleteLastView(recentView.getUserId());
            }
            recentViewRepository.save(recentView);
        }
    }

    @Transactional
    public void deleteLastView(Long userId) {
        //여기서 가장 오래된 recentView 1개 삭제
        recentViewRepository.deleteLastView(userId);
    }

    public List<RecentView> findByUserId(Long userId) {
        return recentViewRepository.findByUserId(userId);
    }
}
