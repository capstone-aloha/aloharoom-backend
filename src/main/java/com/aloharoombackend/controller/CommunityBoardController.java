package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.dto.CommunityBoardDto;
import com.aloharoombackend.dto.CommunityEditDto;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.model.CommunityImage;
import com.aloharoombackend.model.User;
import com.aloharoombackend.service.AwsS3Service;
import com.aloharoombackend.service.CommunityBoardService;
import com.aloharoombackend.service.CommunityImageService;
import com.aloharoombackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/communityboard")
@RequiredArgsConstructor
public class CommunityBoardController {

    public final CommunityBoardService communityBoardService;

    /*//커뮤니티 code1 조회
    @GetMapping("/1")
    public ResponseEntity<List<CommunityAllDto>> getCode1(@RequestParam Integer code1) {
        return ResponseEntity.ok(communityBoardService.findAllByCode(code1));
    }

    //커뮤니티 code2 조회
    @GetMapping("/2")
    public ResponseEntity<List<CommunityAllDto>> getCode2(@RequestParam Integer code2) {
        return ResponseEntity.ok(communityBoardService.findAllByCode(code2));
    }

    //커뮤니티 code3 조회
    @GetMapping("/3")
    public ResponseEntity<List<CommunityAllDto>> getCode3(@RequestParam Integer code3) {
        return ResponseEntity.ok(communityBoardService.findAllByCode(code3));
    }*/

    @GetMapping("code/{code}")
    public ResponseEntity<List<CommunityAllDto>> getAllByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(communityBoardService.findAllByCode(code));
    }
    //커뮤니티 상세 보기
    @GetMapping("/{communityId}")
    public ResponseEntity<CommunityAllDto> getOneCommunity(@PathVariable Long communityId,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(communityBoardService.findCommunityOne(communityId, userId));
    }

    //커뮤니티 글 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity addCommunity(@RequestPart CommunityBoardDto communityBoardDto,
                                          @RequestPart List<MultipartFile> imgFiles,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(communityBoardService.create(communityBoardDto, imgFiles, userId));
    }

    //커뮤니티 수정 시 초기화면
    @GetMapping("/edit/{communityId}")
    public ResponseEntity editCommunityForm(@PathVariable Long communityId) {
        return ResponseEntity.ok(communityBoardService.findOneEdit(communityId));
    }

    //커뮤니티 글 수정
    @PatchMapping(path = {"/edit/{communityId}"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommunityEditDto editCommunity(@RequestPart CommunityEditDto communityEditDto,
                                                        @RequestPart List<MultipartFile> imgFiles,
                                                        @PathVariable Long communityId) {
        return ResponseEntity.ok(communityBoardService.update(communityId, communityEditDto, imgFiles)).getBody();
    }

    //커뮤니티 글 삭제
    @DeleteMapping("/{communityId}")
    public ResponseEntity deleteCommunity(@PathVariable Long communityId) {
        return ResponseEntity.ok(communityBoardService.delete(communityId));
    }

    //내가 쓴 커뮤니티 조회
    @GetMapping("/my/community")
    public ResponseEntity getMyCommunity(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(communityBoardService.getMyCommunity(principalDetails.getUser().getId()));
    }

    //내가 댓글 단 커뮤니티 조회
    @GetMapping("/my/comment")
    public ResponseEntity getCommunityComment(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(communityBoardService.getCommunityComment(principalDetails.getUser().getId()));
    }
}