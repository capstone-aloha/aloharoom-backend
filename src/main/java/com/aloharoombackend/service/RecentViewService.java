package com.aloharoombackend.service;

import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.model.RecentView;
import com.aloharoombackend.repository.BoardRepository;
import com.aloharoombackend.repository.RecentViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class RecentViewService {
    private final RecentViewRepository recentViewRepository;
//    private final BoardService boardService;
    private final BoardRepository boardRepository;

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

    public List<HeartBoardDto> findByUserId(Long userId) {
        List<RecentView> recentViews = recentViewRepository.findByUserId(userId);

        Collections.sort(recentViews, new Comparator<RecentView>() {
            @Override
            public int compare(RecentView o1, RecentView o2) {
                if (o1.getCreatedDate().isBefore(o2.getCreatedDate())) return 1;
                else if (!o1.getCreatedDate().isBefore(o2.getCreatedDate())) return -1;
                else return 0;
            }
        });

        List<Long> boardIds = recentViews.stream().map(recentView -> recentView.getBoardId()).collect(Collectors.toList());
//        List<HeartBoardDto> boards = boardService.findByboardIds(boardIds);
        List<HeartBoardDto> heartBoardDtos = boardRepository.recentViewBoard(boardIds);
        List<HeartBoardDto> heartBoardDtosSort = new ArrayList<>();

        for (int i = 0; i < heartBoardDtos.size(); i++) {
            for (int j = 0; j < heartBoardDtos.size(); j++) {
                if(boardIds.get(i) == heartBoardDtos.get(j).getBoardId()) heartBoardDtosSort.add(heartBoardDtos.get(j));
            }
        }
        return heartBoardDtosSort;
    }

    @Transactional
    public void deleteByBoardId(Long boardId) {
        recentViewRepository.deleteByBoardId(boardId);
    }
}
