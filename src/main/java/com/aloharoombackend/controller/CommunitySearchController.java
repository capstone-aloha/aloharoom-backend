package com.aloharoombackend.controller;

import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.service.CommunityBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/communitySearch")
@RequiredArgsConstructor
public class CommunitySearchController {
    private final CommunityBoardService communityBoardService;

    @GetMapping
    public ResponseEntity<List<CommunityAllDto>> searchCommunity(@RequestParam String keyword,
                                                                 @RequestParam Integer code) {
        return ResponseEntity.ok(communityBoardService.searchCommunity(keyword, code));
    }

    @GetMapping("/nickname")
    public ResponseEntity<List<CommunityAllDto>> searchCommunityByNickName(@RequestParam String nickname,
                                                                           @RequestParam Integer code) {
        return ResponseEntity.ok(communityBoardService.searchCommunityByNickName(nickname, code));
    }
}
