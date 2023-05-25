package com.aloharoombackend.service;

import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.dto.CommunityBoardDto;
import com.aloharoombackend.dto.CommunityEditDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.CommunityBoardRepository;
import com.aloharoombackend.repository.CommunitySearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
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

    //커뮤니티 code로 전체 조회
    /*public List<CommunityAllDto> findAllByCode(Integer code) {
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
    }*/

    //커뮤니티 code로 전체 조회 + Top3
    public List[] findAllByCode(Integer code) {
        List<CommunityBoard> communityBoards = communityBoardRepository.findAll();

        List<User> users = communityBoards.stream().map(
                communityBoard -> communityBoard.getUser()
        ).collect(Collectors.toList());
        users.stream().forEach(user -> userService.findOne(user.getId()));

        // code가 동일한 CommunityBoard만 필터링
        List<CommunityBoard> filteredCommunityBoards = communityBoards.stream()
                .filter(communityBoard -> communityBoard.getCode() == code)
                .collect(Collectors.toList());

        // 모든 CommunityBoard
        List<CommunityAllDto> communityAllDtos = new ArrayList<>();
        for (CommunityBoard communityBoard : filteredCommunityBoards) {
            communityAllDtos.add(new CommunityAllDto(communityBoard));
        }

        // 조회수 Top3 CommunityBoard
        List<CommunityAllDto> topViewCommunityDtos = filteredCommunityBoards.stream()
                .sorted(Comparator.comparing(CommunityBoard::getViews).reversed())
                .limit(3)
                .map(CommunityAllDto::new)
                .collect(Collectors.toList());

        return new List[]{communityAllDtos, topViewCommunityDtos};
    }

    public CommunityBoard findOneFetch(Long id) { // 프록시->실객체 생성
        CommunityBoard findCommunityId = communityBoardRepository.findById(id) //바꿔 이미지 페치조인
                .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티가 존재하지 않습니다."));
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
        commentService.deleteByCommunityBoardId(communityBoard.getId());
        List<CommunityImage> communityImages = communityBoard.getCommunityImages();
        List<String> deleteImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage);

        communityBoardRepository.delete(communityBoard);
        return "커뮤니티 삭제 완료";
    }

    public List<CommunityAllDto> searchCommunity(String keyword, Integer code) {
        List<CommunityBoard> communityBoards;
        if (code != null) {
            communityBoards = communityBoardRepository.findByTitleContainingAndCode(keyword, code);
        } else {
            communityBoards = communityBoardRepository.findByTitleContaining(keyword);
        }
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

    public List<CommunityAllDto> searchCommunityByNickName(String nickname, Integer code) {
        List<CommunityBoard> communityBoards = communitySearchRepository.searchByNickNameAndCode(nickname, code);
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
