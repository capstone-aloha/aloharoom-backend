package com.aloharoombackend.service;

import com.aloharoombackend.dto.*;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<BoardAllDto> findAll() {
        List<Board> boards = boardRepository.findAll();
        List<Home> homes = homeService.findAll();

        //HomeComment 초기화
        boards.stream().forEach(board -> {
            board.getHomeComments().stream().forEach(homeComment -> homeComment.getLayer());
        });

        List<BoardAllDto> boardAllDtos = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            boardAllDtos.add(new BoardAllDto(boards.get(i), homes.get(i)));
        }
        return boardAllDtos;
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
        List<HeartBoardDto> heartBoardDtos = boardRepository.recentViewBoard(boardIds);
        List<HeartBoardDto> heartBoardDtosSort = new ArrayList<>();

        for (int i = 0; i < heartBoardDtos.size(); i++) {
            for (int j = 0; j < heartBoardDtos.size(); j++) {
                if(boardIds.get(i) == heartBoardDtos.get(j).getBoardId()) heartBoardDtosSort.add(heartBoardDtos.get(j));
            }
        }
        return heartBoardDtosSort;
    }

    //내가 쓴 방 조회
    public List<BoardOneDto> getMyBoard(Long userId) {
        Board board = boardRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
        Home home = homeService.findOne(board.getHome().getId());
        List<Board> boards = boardRepository.findAllByUserId(userId);
        List<BoardOneDto> boardOneDtos = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            boardOneDtos.add(new BoardOneDto(boards.get(i), home));
        }
        return boardOneDtos;
    }
}
