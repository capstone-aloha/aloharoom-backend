package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.*;
import com.aloharoombackend.service.*;
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

    /* 해당 영역의 모든 방 조회 */
    @GetMapping
    public ResponseEntity<List<BoardAllDto>> findAllByRange(
            @RequestParam Double southWestLatitude,
            @RequestParam Double southWestLongitude,
            @RequestParam Double northEastLatitude,
            @RequestParam Double northEastLongitude
    ) {
        return ResponseEntity.ok(boardService.findAllByRange(new RangeDto(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude)));
    }

    //게시물 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardOneDto> getBoardOne(@PathVariable Long boardId, 
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails == null)
            return ResponseEntity.ok(boardService.findOneNew(boardId, null));
        else
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

    //방 비활성화 (매칭완료)
    @PostMapping("/deactivate/{boardId}")
    public ResponseEntity deactivateBoard(@PathVariable Long boardId) {
        //Board에 속성 하나 추가하면 될듯 => 전체 글 뿌려줄 때 필터 => 촤근 본 글, 좋아요한 글에서도 없어져야함.
        return ResponseEntity.ok(boardService.deactivate(boardId));
    }

    //방 활성화
    @PostMapping("/activate/{boardId}")
    public ResponseEntity activateBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.activate(boardId));
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

    @GetMapping("/userId/{userId}")
    public ResponseEntity isBoard(@PathVariable Long userId) {
        return ResponseEntity.ok(boardService.isBoard(userId));
    }
}
