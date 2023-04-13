package com.aloharoombackend.controller;

import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.service.CommunityBoardService;
import com.aloharoombackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/communitySearch")
@RequiredArgsConstructor
public class CommunitySearchController {
    private final CommunityBoardService communityBoardService;
    private final UserService userService;

    @GetMapping
    public List<CommunityAllDto> searchCommunity(@RequestParam String keyword) {
        List<CommunityBoard> communityBoards = communityBoardService.searchCommunity(keyword);

        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (int i = 0; i < communityBoards.size(); i++) {
            communityAllDtos.add(new CommunityAllDto(communityBoards.get(i)));
        }
        return communityAllDtos;
    }

    @GetMapping("/nickname")
    public List<CommunityAllDto> searchCommunityByNickName(@RequestParam String nickname) {
        List<CommunityBoard> communityBoards = communityBoardService.searchCommunityByNickName(nickname);

        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (int i = 0; i < communityBoards.size(); i++) {
            communityAllDtos.add(new CommunityAllDto(communityBoards.get(i)));
        }
        return communityAllDtos;
    }
}
