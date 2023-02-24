package com.aloharoombackend.controller;

import com.aloharoombackend.model.Home;
import com.aloharoombackend.model.HomeImage;
import com.aloharoombackend.service.AwsS3Service;
import com.aloharoombackend.service.BoardService;
import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.model.Board;
import com.aloharoombackend.service.HomeImageService;
import com.aloharoombackend.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final AwsS3Service awsS3Service;

    //모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<BoardAllDto>> getAllBoard() {
        List<Board> boards = boardService.findAll();
        List<Home> homes = homeService.findAll();

        List<BoardAllDto> boardAllDtos = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            boardAllDtos.add(new BoardAllDto(boards.get(i), homes.get(i)));
        }
        return ResponseEntity.ok()
                .body(boardAllDtos);
    }

    //게시물 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity addBoard(@RequestPart BoardAddDto boardAddDto, @RequestPart List<MultipartFile> imgFiles) {
        //일단 넣을 수 있는 것만 넣어보자! => Home, HomeImage, Board + (Transportation, 편의시설 => 보류)
        Home home = new Home(boardAddDto);
        Board board = new Board(home, boardAddDto);

        //MultipartFile을 s3에 저장 후 해당 주소로 HomeImage 생성
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<HomeImage> homeImages = imgUrls.stream().map(imgUrl -> new HomeImage(home, imgUrl)).collect(Collectors.toList());

        //DB에 넣어보자 => service 생성 후 사용
        homeService.create(home);
        boardService.create(board);
        for (HomeImage homeImage : homeImages) {
            homeImageService.create(homeImage);
        }

        return ResponseEntity.ok("");
    }
}
