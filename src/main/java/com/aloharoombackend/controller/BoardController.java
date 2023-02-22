package com.aloharoombackend.controller;

import com.aloharoombackend.Service.BoardService;
import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //모든 게시물 조회
    @GetMapping("/api/board/all")
    public ResponseEntity<List<BoardAllDto>> getAllBoard() {
        List<Board> boards = boardService.findAll();
        List<BoardAllDto> boardAllDtos = boards.stream().map(board -> new BoardAllDto(board)).collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(boardAllDtos);
    }
}
