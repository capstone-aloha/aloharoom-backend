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
    public final CommunityImageService communityImageService;
    public final UserService userService;
    public final AwsS3Service awsS3Service;

    //커뮤니티 전체 조회
    @GetMapping
    public List<CommunityAllDto> getAllCommunity() {
        List<CommunityBoard> communityBoards = communityBoardService.findAll();

        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (int i = 0; i < communityBoards.size(); i++) {
            communityAllDtos.add(new CommunityAllDto(communityBoards.get(i)));
        }
        return communityAllDtos;
    }

    //커뮤니티 단건 조회
    @GetMapping("/{communityId}")
    public CommunityAllDto getOneCommunity(@PathVariable Long communityId) {
        CommunityBoard communityBoard = communityBoardService.findOneFetch(communityId);
        CommunityAllDto communityAllDto = new CommunityAllDto(communityBoard);
        return communityAllDto;

    }

    //커뮤니티 글 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommunityBoardDto addCommunity(@RequestPart CommunityBoardDto communityBoardDto,
                                            @RequestPart List<MultipartFile> imgFiles,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = userService.findOne(principalDetails.getUser().getId());
        CommunityBoard communityBoard = new CommunityBoard(user, communityBoardDto);

        //MultipartFile을 s3에 저장 후 해당 주소로 CommunityImages 생성
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<CommunityImage> communityImages = imgUrls.stream().map(imgUrl -> new CommunityImage(communityBoard, imgUrl)).collect(Collectors.toList());

        //DB 저장
        communityBoardService.create(communityBoard);
        communityImageService.create(communityImages);

        return communityBoardDto;
    }

    //커뮤니티 글 수정
    @PatchMapping(path = {"/{communityId}"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommunityEditDto editCommunity(@RequestPart CommunityEditDto communityEditDto,
                                          @RequestPart List<MultipartFile> imgFiles,
                                          @PathVariable Long communityId) {
        CommunityBoard communityBoard = communityBoardService.findOneFetch(communityId); //프록시 초기화

        //이미지 삭제
        List<CommunityImage> communityImages = communityBoard.getCommunityImages();
        communityImages.forEach(communityImageService::delete);
        List<String> deleteImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage);

        //업데이트
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<CommunityImage> newCommunityImages = imgUrls.stream().map(imgUrl -> new CommunityImage(communityBoard, imgUrl)).collect(Collectors.toList());

        communityBoardService.update(communityId, communityEditDto, newCommunityImages);
        communityImageService.create(newCommunityImages);
        return communityEditDto;
    }

    //커뮤니티 글 삭제
    @DeleteMapping("/{communityId}")
    public ResponseEntity deleteCommunity(@PathVariable Long communityId) {
        CommunityBoard communityBoard = communityBoardService.findOneFetch(communityId); //프록시 초기화
        List<CommunityImage> communityImages = communityBoard.getCommunityImages();
        communityImages.forEach(communityImageService::delete);

        List<String> deleteImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage);
        communityBoardService.delete(communityBoard);

        return ResponseEntity.ok("");
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