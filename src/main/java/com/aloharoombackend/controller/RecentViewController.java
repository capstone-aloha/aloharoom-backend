package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.model.RecentView;
import com.aloharoombackend.service.BoardService;
import com.aloharoombackend.service.CommunityBoardService;
import com.aloharoombackend.service.RecentViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recentView")
@RequiredArgsConstructor
public class RecentViewController {
    private final RecentViewService recentViewService;
    private final BoardService boardService;
    private final CommunityBoardService communityBoardService;

    @GetMapping
    public ResponseEntity getRecentView(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(recentViewService.findByUserId(principalDetails.getUser().getId()));
    }
}
