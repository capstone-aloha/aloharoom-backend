package com.aloharoombackend.service;

import com.aloharoombackend.dto.*;
import com.aloharoombackend.model.Home;
import com.aloharoombackend.model.RecentView;
import com.aloharoombackend.model.User;
import com.aloharoombackend.repository.BoardRepository;
import com.aloharoombackend.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final HomeService homeService;
    private final UserService userService;
    private final RecentViewService recentViewService;

    @Transactional
    public Board create(Board board) {
        return boardRepository.save(board);
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
    }
    
    @Transactional //최근 본 글 때문에 추가
    public BoardOneDto findOneNew(Long boardId, Long loginUserId) {
        RecentView recentView = new RecentView(boardId, loginUserId);
        recentViewService.create(recentView);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
        Home home = homeService.findOne(board.getHome().getId());
        Long userId = board.getUser().getId();
        User user = userService.findOneFetch(userId);
        return new BoardOneDto(board, home, user);
    }

    public List<Board> findAll() {
        List<Board> boards = boardRepository.findAll();
        //HomeComment 초기화
        boards.stream().forEach(board -> {
            board.getHomeComments().stream().forEach(homeComment -> homeComment.getLayer());
        });
        return boards;
    }

    @Transactional
    public Board update(Long boardId, BoardEditDto boardEditDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
        return board.change(boardEditDto);
    }

    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }

    public List<BoardAllDto> searchFilter(SearchFilterDto searchFilterDto) {
        return boardRepository.searchFilter(searchFilterDto);
    }

    public List<HeartBoardDto> findByboardIds(List<Long> boardIds) {
        return boardRepository.recentViewBoard(boardIds);
    }

}
