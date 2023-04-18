package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.BoardEditDto;
import com.aloharoombackend.dto.BoardOneDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.service.*;
import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardAllDto;
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
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final HomeService homeService;
    private final HomeImageService homeImageService;
    private final UserService userService;
    private final AwsS3Service awsS3Service;

    //모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<BoardAllDto>> getAllBoard() {
        return ResponseEntity.ok()
                .body(boardService.findAll());
    }

    //게시물 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardOneDto> getBoardOne(@PathVariable Long boardId, 
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long loginUserId = principalDetails.getUser().getId();
        return ResponseEntity.ok(boardService.findOneNew(boardId, loginUserId));
    }

    //게시물 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity addBoard(@RequestPart BoardAddDto boardAddDto, @RequestPart List<MultipartFile> imgFiles,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Home home = new Home(boardAddDto);
        User user = userService.findOne(principalDetails.getUser().getId());
//        User user = userService.findOne(1L);
        Board board = new Board(home, user, boardAddDto);

        //MultipartFile을 s3에 저장 후 해당 주소로 HomeImage 생성
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<HomeImage> homeImages = imgUrls.stream().map(imgUrl -> new HomeImage(home, imgUrl)).collect(Collectors.toList());

        homeService.create(home);
        boardService.create(board);

        return ResponseEntity.ok("");
    }

    //게시물 수정
    @PatchMapping(path = {"/{boardId}"},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity editBoard(@RequestPart BoardEditDto boardEditDto,
                                    @RequestPart List<MultipartFile> imgFiles,
                                    @PathVariable Long boardId) {
        Board board = boardService.findOne(boardId);
        Long homeId = board.getHome().getId();

        //home에 있는 이미지 삭제 => aws 삭제, HomeImage 삭제
        Home home1 = homeService.findOne(homeId);
        List<HomeImage> homeImages = home1.getHomeImages();
        homeImages.forEach(homeImageService::delete); // HomeImage 삭제
        List<String> deleteImgUrls = homeImages.stream().map(hi -> hi.getImgUrl()).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage); // aws 삭제

        //업데이트
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<HomeImage> newHomeImages = imgUrls.stream().map(imgUrl -> new HomeImage(home1, imgUrl)).collect(Collectors.toList());

//        for (HomeImage homeImage : newHomeImages) {
//            homeImageService.create(homeImage);
//        }
        boardService.update(boardId, boardEditDto);
        homeService.update(homeId, boardEditDto, newHomeImages);

        return ResponseEntity.ok("");
    }

    //게시물 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId) {
        //board, home, homeImage 삭제
        Board board = boardService.findOne(boardId);
        Home home = homeService.findOne(board.getHome().getId());
        List<HomeImage> homeImages = home.getHomeImages();


        List<String> deleteImgUrls = homeImages.stream().map(hi -> hi.getImgUrl()).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage); // aws 삭제
        boardService.delete(board);
        homeService.delete(home); //Home이 HomeImage의 생명주기를 관리하므로 Home을 삭제하면 연관된 HomeImage들도 삭제된다.

        return ResponseEntity.ok("");
    }

    //내가 댓글 단 방 조회
    @GetMapping("/my/comment")
    public ResponseEntity getBoardComment(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(boardService.getBoardComment(principalDetails.getUser().getId()));
    }
}
