package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    //좋아요 추가
    @PostMapping("/{boardId}")
    public ResponseEntity addHeart(
            @PathVariable Long boardId,
            @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(heartService.save(boardId, principal.getUser().getId()));
    }

    //좋아요 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteHeart(
            @PathVariable Long boardId,
            @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(heartService.delete(boardId, principal.getUser().getId()));
    }

    //내 좋아요 리스트 조회 => 카드형식
    @GetMapping
    public ResponseEntity getHeartList(@AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(heartService.findByHeartBoard(principal.getUser().getId()));
    }
}
