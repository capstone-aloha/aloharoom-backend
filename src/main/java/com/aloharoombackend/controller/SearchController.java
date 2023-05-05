package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.model.User;
import com.aloharoombackend.service.BoardService;
import com.aloharoombackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardAllDto>> searchFilter(@RequestBody SearchFilterDto searchFilterDto,
                                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(boardService.searchFilter(principalDetails.getUser().getId(), searchFilterDto));
    }
}