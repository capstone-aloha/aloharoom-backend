package com.aloharoombackend.service;

import com.aloharoombackend.dto.*;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final HomeService homeService;
    private final UserService userService;
    private final RecentViewService recentViewService;
    private final CommentService commentService;
    private final AwsS3Service awsS3Service;
    private final HomeImageService homeImageService;
    private final HeartService heartService;

    @Transactional
    public String create(BoardAddDto boardAddDto, List<MultipartFile> imgFiles, Long loginUserId) {
        Home home = new Home(boardAddDto);
        User user = userService.findOne(loginUserId);
        Board board = new Board(home, user, boardAddDto);

        //MultipartFile을 s3에 저장 후 해당 주소로 HomeImage 생성
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<HomeImage> homeImages = imgUrls.stream().map(imgUrl -> new HomeImage(home, imgUrl)).collect(Collectors.toList());

        homeService.create(home);
        boardRepository.save(board);
        homeImages.stream().forEach(homeImage -> homeImageService.create(homeImage));
        return "방 작성 완료";
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
    }

    public BoardEditDto findOneEditForm(Long boardId) {
        Board board = findOne(boardId);
        //이떄, Home 조회 쿼리 따로 나가면 안됨. => 나중에 확인
        return new BoardEditDto(board, board.getHome());
    }

    @Transactional //최근 본 글 때문에 추가
    public BoardOneDto findOneNew(Long boardId, Long loginUserId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
        Home home = board.getHome();
        System.out.println("Images = " + home.getHomeImages().get(0)); //이걸 해야만 HomeImage가 실객체로 변환됨.

        Long userId = board.getUser().getId();
        User user = userService.findOneFetch(userId);
        Boolean isHeart = false;
        if(loginUserId != null) {
            if(board.getActivation()) {
                RecentView recentView = new RecentView(boardId, loginUserId);
                recentViewService.create(recentView);
                isHeart = heartService.findByBoardIdAndUserId(boardId, loginUserId);
            }
        }
        return new BoardOneDto(board, home, user, isHeart);
    }

    public List<BoardAllDto> findAllByRange(RangeDto rangeDto) {
        List<Board> boards = boardRepository.findAllByRange(rangeDto);

        //HomeComment 초기화
        boards.stream().forEach(board -> {
            board.getComments().stream().forEach(comment -> comment.getLayer());
        });

        List<BoardAllDto> boardAllDtos = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            boardAllDtos.add(new BoardAllDto(boards.get(i), boards.get(i).getHome()));
        }
        return boardAllDtos;
    }

    @Transactional
    public Board update(Long boardId, BoardEditDto boardEditDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
        return board.change(boardEditDto);
    }

    @Transactional
    public String updateNew(BoardEditDto boardEditDto, List<MultipartFile> imgFiles, Long boardId) {
        Long homeId = findOne(boardId).getHome().getId();

        //home에 있는 이미지 삭제 => aws 삭제, HomeImage 삭제
        Home home1 = homeService.findOne(homeId);
        List<HomeImage> homeImages = home1.getHomeImages();
        List<String> deleteImgUrls = homeImages.stream().map(hi -> hi.getImgUrl()).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage); // aws 삭제
        homeImages.stream().forEach(homeImage -> homeImageService.delete(homeImage));

        //업데이트
        List<String> imgUrls = awsS3Service.uploadImage(imgFiles);
        List<HomeImage> newHomeImages = imgUrls.stream().map(imgUrl -> new HomeImage(home1, imgUrl)).collect(Collectors.toList());
        newHomeImages.stream().forEach(newHomeImage -> homeImageService.create(newHomeImage));
        homeService.update(homeId, boardEditDto, newHomeImages);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
        board.change(boardEditDto);
        return "방 수정 완료";
    }

    @Transactional
    public String delete(Long boardId) {
        Board board = findOne(boardId);
        Home home = homeService.findOne(board.getHome().getId());
        List<HomeImage> homeImages = home.getHomeImages();

        List<String> deleteImgUrls = homeImages.stream().map(hi -> hi.getImgUrl()).collect(Collectors.toList());
        deleteImgUrls.forEach(awsS3Service::deleteImage); // aws 삭제

        homeImages.stream().forEach(homeImage -> homeImageService.delete(homeImage));

        commentService.deleteByBoardId(boardId); //해당 글의 댓글 삭제
        heartService.deleteByBoardId(boardId); //해당 글의 좋아요 삭제
        recentViewService.deleteByBoardId(boardId); //해당 글의 최근 본 글 삭제

        boardRepository.delete(board);
        homeService.delete(home); //Home이 HomeImage의 생명주기를 관리하므로 Home을 삭제하면 연관된 HomeImage들도 삭제된다.

        return "방 삭제 완료";
    }

    public List<BoardAllDto> searchFilter(RangeDto rangeDto, SearchFilterDto searchFilterDto) {
        return boardRepository.searchFilter(rangeDto, searchFilterDto);
    }

    public List<HeartBoardDto> findByboardIds(List<Long> boardIds) {
        List<HeartBoardDto> heartBoardDtos = boardRepository.recentViewBoard(boardIds);
        List<HeartBoardDto> heartBoardDtosSort = new ArrayList<>();

        for (int i = 0; i < heartBoardDtos.size(); i++) {
            for (int j = 0; j < heartBoardDtos.size(); j++) {
                if(boardIds.get(i) == heartBoardDtos.get(j).getBoardId()) heartBoardDtosSort.add(heartBoardDtos.get(j));
            }
        }
        return heartBoardDtosSort;
    }

    /* 방 비활성화 (매칭완료) */
    @Transactional
    public String deactivate(Long boardId) {
        findOne(boardId).deactivate();
        heartService.deleteByBoardId(boardId);
        recentViewService.deleteByBoardId(boardId);
        return "방 비활성화 완료";
    }

    /* 방 활성화 */
    @Transactional
    public String activate(Long boardId) {
        //원래 있던 방 댓글 삭제
        commentService.deleteByBoardId(boardId);
        findOne(boardId).activate();
        return "방 활성화 완료";
    }

    //내가 쓴 방 조회(카드형식)
    public BoardAllDto getMyBoard(Long userId) {
        Board board = boardRepository.findByUserId(userId);
        Home home = homeService.findOne(board.getHome().getId());
        BoardAllDto boardAllDto = new BoardAllDto(board, home);
        return boardAllDto;
    }

    //내가 댓글 단 방 조회
    public List<BoardAllDto> getBoardComment(Long userId) { //매개변수 나중에 DTO로 변환 => userId만 받는
        List<Comment> myComments = commentService.findMyComment(userId);
        List<Comment> myBoardComments = myComments.stream().filter(myComment ->
                myComment.getCommunityBoard() == null).collect(Collectors.toList());
        List<Comment> myDistinctBoardComments = new ArrayList<>();

        Set<Long> set = new HashSet<>();
        for (Comment myBoardComment : myBoardComments) {
            if(set.add(myBoardComment.getBoard().getId()))
                myDistinctBoardComments.add(myBoardComment);
        }
        //Home 초기화 => in쿼리로 쿼리 갯수 감소
        for (Comment myDistinctBoardComment : myDistinctBoardComments) {
            myDistinctBoardComment.getBoard().getHome().getId();
        }
        List<BoardAllDto> boardAllDtos = myDistinctBoardComments.stream().map(myDistinctBoardComment -> new BoardAllDto(myDistinctBoardComment.getBoard(), myDistinctBoardComment.getBoard().getHome()))
                .collect(Collectors.toList());
        return boardAllDtos;
    }

    public Boolean isBoard(Long userId) {
        User user = userService.findOne(userId);
        if(user.getBoard() == null) return false;
        else return true;
    }
}
