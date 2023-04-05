package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.CommunityBoardDto;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.model.CommunityImage;
import com.aloharoombackend.model.User;
import com.aloharoombackend.service.AwsS3Service;
import com.aloharoombackend.service.CommunityBoardService;
import com.aloharoombackend.service.CommunityImageService;
import com.aloharoombackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    //커뮤니티 글 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommunityBoardDto addCommunityBoard(@RequestPart CommunityBoardDto communityBoardDto,
                                            @RequestPart List<MultipartFile> imgFiles,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = userService.findOne(principalDetails.getUser().getId());
        
        //MultipartFile을 s3에 저장 후 해당 주소로 CommunityImages 생성
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<CommunityImage> communityImages = imgUrls.stream().map(imgUrl -> new CommunityImage(imgUrl)).collect(Collectors.toList());

        CommunityBoard communityBoard = new CommunityBoard(user, communityBoardDto, communityImages);

        //DB 저장
        communityBoardService.create(communityBoard);
        communityImageService.create(communityImages);

        return communityBoardDto;
    }
}
