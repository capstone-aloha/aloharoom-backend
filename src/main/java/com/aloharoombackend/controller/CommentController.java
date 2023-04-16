package com.aloharoombackend.controller;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.EditCommentDto;
import com.aloharoombackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping
    public ResponseEntity addComment(@RequestBody AddCommentDto addCommentDto) {
        return ResponseEntity.ok(commentService.addComment(addCommentDto));
    }

    //댓글 수정
    @PatchMapping
    public ResponseEntity editComment(@RequestBody EditCommentDto editCommentDto) {
        return ResponseEntity.ok(commentService.editComment(editCommentDto));
    }

    //댓글 조회
    @GetMapping("/home/{boardId}")
    public ResponseEntity getHomeComment(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getHomeComment(boardId));
    }
    @GetMapping("/community/{boardId}")
    public ResponseEntity getCommunityComment(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommunityComment(boardId));
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    private ResponseEntity deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
