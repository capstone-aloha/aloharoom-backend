package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.BoardEditDto;
import com.aloharoombackend.dto.BoardOneDto;
import com.aloharoombackend.service.*;
import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardAllDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<BoardAllDto>> getAllBoard() {
        return ResponseEntity.ok(boardService.findAll());
    }

    //게시물 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardOneDto> getBoardOne(@PathVariable Long boardId, 
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(boardService.findOneNew(boardId, principalDetails.getUser().getId()));
    }

    //게시물 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity addBoard(@RequestPart BoardAddDto boardAddDto, @RequestPart List<MultipartFile> imgFiles,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(boardService.create(boardAddDto, imgFiles, principalDetails.getUser().getId()));
    }

    //방 수정 화면
    @GetMapping("/edit/{boardId}")
    public ResponseEntity editBoardForm(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.findOneEditForm(boardId));
    }

    //방 수정
    @PatchMapping(path = {"/edit/{boardId}"},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity editBoard(@RequestPart BoardEditDto boardEditDto,
                                    @RequestPart List<MultipartFile> imgFiles,
                                    @PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.updateNew(boardEditDto, imgFiles, boardId));
    }

    //게시물 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.delete(boardId));
    }

    //내가 쓴 방 조회
    @GetMapping("/my/board")
    public ResponseEntity getMyBoard(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(boardService.getMyBoard(principalDetails.getUser().getId()));
    }

    //내가 댓글 단 방 조회
    @GetMapping("/my/comment")
    public ResponseEntity getBoardComment(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(boardService.getBoardComment(principalDetails.getUser().getId()));
    }
}
