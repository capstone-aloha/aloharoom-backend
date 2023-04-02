package com.aloharoombackend.controller;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.CommentDto;
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

    //댓글 조회
    @GetMapping("/{boardId}")
    public ResponseEntity getComment(@PathVariable Long boardId) {
        return ResponseEntity.ok(homeCommentService.getComment(boardId));
    }
}
