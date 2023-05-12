package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.RangeDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.service.BoardService;
import com.aloharoombackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final UserService userService;
    private final BoardService boardService;

//    @GetMapping
//    public ResponseEntity<List<BoardAllDto>> searchFilter(
//                                                          @RequestParam String gender,
//                                                          @RequestParam Integer roomCount,
//                                                          @RequestParam String homeType,
//                                                          @RequestParam List<Integer> ageRange,
//                                                          @RequestParam List<Integer> flatRange,
//                                                          @RequestParam List<Integer> rentRange,
//                                                          @RequestParam List<String> likeHashtags,
//                                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        SearchFilterDto searchFilterDto = new SearchFilterDto(gender, roomCount, homeType, ageRange, flatRange, rentRange, likeHashtags);
//        System.out.println(searchFilterDto);
//        return ResponseEntity.ok(boardService.searchFilter(principalDetails.getUser().getId(), searchFilterDto));
//    }

    @GetMapping
    public ResponseEntity<List<BoardAllDto>> searchFilter(
            @RequestParam Double southWestLatitude, @RequestParam Double southWestLongitude,
            @RequestParam Double northEastLatitude, @RequestParam Double northEastLongitude,
            @RequestParam String gender, @RequestParam Integer roomCount,
            @RequestParam String homeType, @RequestParam List<Integer> ageRange,
            @RequestParam List<Integer> flatRange, @RequestParam List<Integer> rentRange,
            @RequestParam List<String> likeHashtags, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<BoardAllDto> boards = boardService.findAllByRange(new RangeDto(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude));
        SearchFilterDto searchFilterDto = new SearchFilterDto(gender, roomCount, homeType, ageRange, flatRange, rentRange, likeHashtags);
        System.out.println(searchFilterDto);
        RangeDto rangeDto = new RangeDto(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude);
        //영역 구분하는 쿼리 추가
        return ResponseEntity.ok(boardService.searchFilter(rangeDto, searchFilterDto));
    }
}