package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.model.Heart;
import com.aloharoombackend.model.HeartId;
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
        Long userId = principal.getUser().getId();
        Heart heart = new Heart(boardId, userId);
        heartService.save(heart);
        return ResponseEntity.ok("");
    }

    //좋아요 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteHeart(
            @PathVariable Long boardId,
            @AuthenticationPrincipal PrincipalDetails principal) {
        Long userId = principal.getUser().getId();
        HeartId heartId = new HeartId(boardId, userId);
        heartService.delete(heartId);
        return ResponseEntity.ok("삭제 완료");
    }

}
