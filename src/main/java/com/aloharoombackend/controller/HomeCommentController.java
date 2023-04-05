package com.aloharoombackend.controller;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.EditCommentDto;
import com.aloharoombackend.service.HomeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homeComment")
@RequiredArgsConstructor
public class HomeCommentController {
    private final HomeCommentService homeCommentService;

    //댓글 작성
    @PostMapping
    public ResponseEntity addComment(@RequestBody AddCommentDto addCommentDto) {
        return ResponseEntity.ok(homeCommentService.addComment(addCommentDto));
    }

    //댓글 수정
    @PatchMapping
    public ResponseEntity editComment(@RequestBody EditCommentDto editCommentDto) {
        return ResponseEntity.ok(homeCommentService.editComment(editCommentDto));
    }

    //댓글 조회
    @GetMapping("/{boardId}")
    public ResponseEntity getComment(@PathVariable Long boardId) {
        return ResponseEntity.ok(homeCommentService.getComment(boardId));
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    private ResponseEntity deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(homeCommentService.deleteComment(commentId));
    }
}
