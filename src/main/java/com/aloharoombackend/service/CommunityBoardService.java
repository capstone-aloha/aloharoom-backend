package com.aloharoombackend.service;

import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.dto.CommunityBoardDto;
import com.aloharoombackend.dto.CommunityEditDto;
import com.aloharoombackend.model.Comment;
import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.model.CommunityImage;
import com.aloharoombackend.model.User;
import com.aloharoombackend.repository.CommunityBoardRepository;
import com.aloharoombackend.repository.CommunitySearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityBoardService{
    private final UserService userService;
    private final AwsS3Service awsS3Service;
    private final CommunityImageService communityImageService;
    private final CommunityBoardRepository communityBoardRepository;
    private final CommunitySearchRepository communitySearchRepository;
    private final CommentService commentService;
    private Map<Long, List<Long>> viewsMap = new HashMap<>();


    //커뮤니티 생성
    @Transactional
    public CommunityBoardDto create(CommunityBoardDto communityBoardDto, List<MultipartFile> imgFiles, Long userId) {
        User user = userService.findOne(userId);
        CommunityBoard communityBoard = new CommunityBoard(user, communityBoardDto);

        //imgFiles -> s3 저장 ->  그 주소로 CommunityImages 생성
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<CommunityImage> communityImages = imgUrls.stream().map(imgUrl -> new CommunityImage(communityBoard, imgUrl)).collect(Collectors.toList());

        //DB 저장
        communityBoardRepository.save(communityBoard);
        communityImageService.create(communityImages);

        return communityBoardDto;
    }

    public CommunityBoard findOne(Long communityId) {
        return communityBoardRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 않습니다."));
    }

    public List<CommunityAllDto> findAll() {
        List<CommunityBoard> communityBoards = communityBoardRepository.findAll();
        communityBoards.stream().forEach(communityBoard -> {
            communityBoard.getCommunityImages().stream()
                    .forEach(communityImage -> communityImage.getId());
        });

        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (int i = 0; i < communityBoards.size(); i++) {
            communityAllDtos.add(new CommunityAllDto(communityBoards.get(i)));
        }
        return communityAllDtos;
    }

    public List<CommunityAllDto> findAllByCode(Integer code) {
        List<CommunityBoard> communityBoards = communityBoardRepository.findAll();

        //code가 동일한 CommunityBoard만 필터링
        communityBoards = communityBoards.stream()
                .filter(communityBoard -> communityBoard.getCode() == code)
                .collect(Collectors.toList());

        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (CommunityBoard communityBoard : communityBoards) {
            communityAllDtos.add(new CommunityAllDto(communityBoard));
        }
        return communityAllDtos;
    }

    public CommunityBoard findOneFetch(Long id) { // 프록시->실객체 생성
        CommunityBoard findCommunityId = communityBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 않습니다."));
        findCommunityId.getCommunityImages().stream().
                forEach(CommunityImage::getImgUrl);
        return findCommunityId;
    }

    //상세 보기
    @Transactional
    public CommunityAllDto findCommunityOne(Long communityId, Long userId) {
        CommunityBoard communityBoard = findOneFetch(communityId);
        updateViews(communityId, userId);
        return new CommunityAllDto(communityBoard);
    }

    //조회수 증가
    @Transactional
    public void updateViews(Long id, Long userId) {
        CommunityBoard communityBoard = communityBoardRepository.findById(id).get();
        List<Long> userIdList = viewsMap.computeIfAbsent(id, k -> new ArrayList<>());
        if (!userIdList.contains(userId)) {
            userIdList.add(userId);
            communityBoard.updateViews(communityBoard.getViews());
        }
    }

    //수정 시 초기화면
    public CommunityAllDto findOneEdit(Long communityId) {
        CommunityBoard communityBoard = findOne(communityId);
        return new CommunityAllDto(communityBoard);
    }

    @Transactional
    public CommunityEditDto update(Long communityId, CommunityEditDto communityEditDto, List<MultipartFile> imgFiles) {
        CommunityBoard communityBoard = findOneFetch(communityId);

        //이미지 삭제
        List<CommunityImage> communityImages = communityBoard.getCommunityImages();
        communityImages.forEach(communityImageService::delete);
        List<String> deleteImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage);

        //업데이트
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<CommunityImage> newCommunityImages = imgUrls.stream().map(imgUrl -> new CommunityImage(communityBoard, imgUrl)).collect(Collectors.toList());
        communityBoard.change(communityEditDto, newCommunityImages);
        return new CommunityEditDto(communityBoard);
    }

    @Transactional
    public String delete(Long communityId) {
        CommunityBoard communityBoard = findOneFetch(communityId);
        List<CommunityImage> communityImages = communityBoard.getCommunityImages();
        List<String> deleteImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage);

        communityBoardRepository.delete(communityBoard);
        return "커뮤니티 삭제 완료";
    }

    public List<CommunityBoard> searchCommunity(String keyword) {
        List<CommunityBoard> communityBoards = communityBoardRepository.findByTitleContaining(keyword);
        communityBoards.stream().forEach(communityBoard -> {
            communityBoard.getCommunityImages().stream()
                    .forEach(communityImage -> communityImage.getId());
        });
        return communityBoards;
    }

    public List<CommunityBoard> searchCommunityByNickName(String nickname) {
        List<CommunityBoard> communityBoards = communitySearchRepository.searchByNickName(nickname);
        communityBoards.stream().forEach(communityBoard -> {
            communityBoard.getCommunityImages().stream()
                    .forEach(communityImage -> communityImage.getId());
        });
        return communityBoards;
    }

    //내가 쓴 커뮤니티 조회
    public List<CommunityAllDto> getMyCommunity(Long userId) {
        List<CommunityBoard> communityBoards = communityBoardRepository.findAllByUserId(userId);
        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (int i = 0; i < communityBoards.size(); i++) {
            communityAllDtos.add(new CommunityAllDto(communityBoards.get(i)));
        }
        return communityAllDtos;
    }

    //내가 댓글 단 커뮤니티 조회
    public List<CommunityAllDto> getCommunityComment(Long userId) { //매개변수 나중에 DTO로 변환 => userId만 받는
        List<Comment> myComments = commentService.findMyComment(userId);
        List<Comment> myCommunityComments = myComments.stream().filter(myComment ->
                myComment.getBoard() == null).collect(Collectors.toList());
        List<CommunityAllDto> communityAllDtos = myCommunityComments.stream()
                .map(myCommunityComment -> new CommunityAllDto(myCommunityComment.getCommunityBoard()))
                .collect(Collectors.toList());
        return communityAllDtos;
    }
}
