package com.aloharoombackend.service;

import com.aloharoombackend.dto.MyPageDto;
import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.dto.UserInfoDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final LikeHomeHashtagService likeHomeHashtagService;
    private final MyHomeHashtagService myHomeHashtagService;
    private final LikeHashtagService likeHashtagService;
    private final MyHashtagService myHashtagService;
    private final AwsS3Service awsS3Service;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 탈퇴 시 순환참조로 인해 선언
     */
    private final CommunityBoardRepository communityBoardRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final HomeRepository homeRepository;
    private final HeartRepository heartRepository;
    private final RecentViewRepository recentViewRepository;

    @Transactional
    public String signUp(SignUpDto signUpDto) {
        User user = new User(signUpDto);
        user.setRole("ROLE_USER");
        user.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/profile.png");
        String rawPassword = user.getPassword(); //입력받은 pw
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //인코딩한 pw
        user.setPassword(encPassword);

        List<LikeHomeHashtag> likeHomeHashtags = signUpDto.getLikeHomeHashtags()
                .stream().map(likeProduct -> new LikeHomeHashtag(likeProduct, user)).collect(Collectors.toList());
        List<MyHomeHashtag> myHomeHashtags = signUpDto.getMyHomeHashtags()
                .stream().map(myProduct -> new MyHomeHashtag(myProduct, user)).collect(Collectors.toList());
        List<LikeHashtag> likeHashtags = signUpDto.getLikeHashtags()
                .stream().map(likeHashtag -> new LikeHashtag(likeHashtag, user)).collect(Collectors.toList());
        List<MyHashtag> myHashtags = signUpDto.getMyHashtags()
                .stream().map(myHashtag -> new MyHashtag(myHashtag, user)).collect(Collectors.toList());

        userRepository.save(user);
        likeHomeHashtagService.create(likeHomeHashtags);
        myHomeHashtagService.create(myHomeHashtags);
        likeHashtagService.create(likeHashtags);
        myHashtagService.create(myHashtags);
        return "회원가입 완료";
    }

    //username 중복 체크
    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

    //nickname 중복 체크
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
    }

    //프록시 -> 실객체 생성, 게시물 단건 조회 사용
    public User findOneFetch(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
        findUser.getMyHashtags().stream().
                forEach(myHashtag -> myHashtag.getHash());
        findUser.getMyHomeHashtags().stream().
                forEach(myProduct -> myProduct.getName());
        return findUser;
    }

    //프록시 -> 실객체 생성, 유저 조회 사용
    public User findOneFetchAll(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
        findUser.getMyHashtags().stream().
                forEach(MyHashtag::getHash);
        findUser.getMyHomeHashtags().stream().
                forEach(MyHomeHashtag::getName);
        findUser.getLikeHashtags().stream().
                forEach(LikeHashtag::getHash);
        findUser.getLikeHomeHashtags().stream().
                forEach(LikeHomeHashtag::getName);
        return findUser;
    }

    //회원 조회
    public MyPageDto findUser(Long userId) {
        User findUser = findOneFetchAll(userId);
        return new MyPageDto(findUser);
    }

    //선호 해시태그, 회원의 해시태그 조회
    public UserInfoDto findOneInfo(Long userId) {
        User findUser = findOneFetchAll(userId);
        return new UserInfoDto(findUser);
    }

    //회원 수정 시 초기 화면
    public MyPageDto findUserEdit(Long userId) {
        User findUser = findOne(userId);
        return new MyPageDto(findUser);
    }

    //영속성 컨텍스트
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    //회원 수정
    @Transactional
    public String update(Long userId, MyPageEditDto myPageEditDto, MultipartFile profileImg) {
        User findUser = getUserById(userId);
        String profileUrl = awsS3Service.uploadProfile(profileImg);

        //삭제
        List<LikeHashtag> likeHashtags = findUser.getLikeHashtags();
        List<LikeHomeHashtag> likeHomeHashtags = findUser.getLikeHomeHashtags();
        List<MyHashtag> myHashtags = findUser.getMyHashtags();
        List<MyHomeHashtag> myHomeHashtags = findUser.getMyHomeHashtags();

        likeHomeHashtags.forEach(likeHomeHashtagService::delete);
        myHomeHashtags.forEach(myHomeHashtagService::delete);
        likeHashtags.forEach(likeHashtagService::delete);
        myHashtags.forEach(myHashtagService::delete);

        //리스트 비우기
        likeHomeHashtags.clear();
        myHomeHashtags.clear();
        likeHashtags.clear();
        myHashtags.clear();

        // 새로 생성
        if (myPageEditDto.getLikeHashtags() != null && !myPageEditDto.getLikeHashtags().isEmpty()) {
            List<LikeHashtag> newLikeHashtags = myPageEditDto.getLikeHashtags()
                    .stream().map(likeHashtag -> new LikeHashtag(likeHashtag, findUser))
                    .collect(Collectors.toList());
            likeHashtagService.create(newLikeHashtags);
        }

        if (myPageEditDto.getLikeHomeHashtags() != null && !myPageEditDto.getLikeHomeHashtags().isEmpty()) {
            List<LikeHomeHashtag> newLikeHomeHashtags = myPageEditDto.getLikeHomeHashtags()
                    .stream().map(likeHomeHashtag -> new LikeHomeHashtag(likeHomeHashtag, findUser))
                    .collect(Collectors.toList());
            likeHomeHashtagService.create(newLikeHomeHashtags);
        }

        if (myPageEditDto.getMyHashtags() != null && !myPageEditDto.getMyHashtags().isEmpty()) {
            List<MyHashtag> newMyHashtags = myPageEditDto.getMyHashtags()
                    .stream().map(myHashtag -> new MyHashtag(myHashtag, findUser))
                    .collect(Collectors.toList());
            myHashtagService.create(newMyHashtags);
        }

        if (myPageEditDto.getMyHomeHashtags() != null && !myPageEditDto.getMyHomeHashtags().isEmpty()) {
            List<MyHomeHashtag> newMyHomeHashtags = myPageEditDto.getMyHomeHashtags()
                    .stream().map(myHomeHashtag -> new MyHomeHashtag(myHomeHashtag, findUser))
                    .collect(Collectors.toList());
            myHomeHashtagService.create(newMyHomeHashtags);
        }

        if(!Objects.equals(findUser.getProfileUrl(), "https://test-aloha1.s3.ap-northeast-2.amazonaws.com/profile.png")) {
            awsS3Service.deleteImage(findUser.getProfileUrl());
        }
        //업데이트
        findUser.edit(myPageEditDto, profileUrl);
        return "수정 성공";
    }

    public String findUserPassword(Long userId) {
        User findUser = findOne(userId);
        return findUser.getPassword();
    }

    @Transactional
    public String changePassword(Long userId, String newPassword) {
        User findUser = findOne(userId);
        String encPassword = bCryptPasswordEncoder.encode(newPassword);
        findUser.setPassword(encPassword);

        return "비밀번호 수정 성공";
    }

    @Transactional
    public String delete(Long userId) {
        User findUser = getUserById(userId);
        List<LikeHashtag> likeHashtags = findUser.getLikeHashtags();
        List<LikeHomeHashtag> likeHomeHashtags = findUser.getLikeHomeHashtags();
        List<MyHashtag> myHashtags = findUser.getMyHashtags();
        List<MyHomeHashtag> myHomeHashtags = findUser.getMyHomeHashtags();

        // userId에 해당하는 모든 Board 데이터를 삭제
        List<Board> boards = boardRepository.findAllByUserId(userId);
        for (Board board : boards) {
            Long boardId = board.getId();
            Home home = homeRepository.findById(board.getHome().getId())
                    .orElseThrow(() -> new IllegalArgumentException("찾는 집이 존재하지 않습니다."));
            List<HomeImage> homeImages = home.getHomeImages();

            List<String> deleteImgUrls = homeImages.stream().map(hi -> hi.getImgUrl()).collect(Collectors.toList());
            deleteImgUrls.forEach(awsS3Service::deleteImage); // aws 삭제

            commentRepository.deleteByBoardId(boardId); //해당 글의 댓글 삭제
            heartRepository.deleteByBoardId(boardId); //해당 글의 좋아요 삭제
            recentViewRepository.deleteByBoardId(boardId); //해당 글의 최근 본 글 삭제
            boardRepository.deleteAll(boards);
            homeRepository.delete(home); //Home이 HomeImage의 생명주기를 관리하므로 Home을 삭제하면 연관된 HomeImage들도 삭제된다.
        }

        // userId에 해당하는 모든 communityBoard 데이터를 삭제
        List<CommunityBoard> communityBoards = communityBoardRepository.findAllByUserId(userId);
        for (CommunityBoard communityBoard : communityBoards) {
            Long boardId = communityBoard.getId();
            commentRepository.deleteByCommunityBoardId(boardId);
            List<CommunityImage> communityImages = communityBoard.getCommunityImages();
            List<String> deleteCommunityImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
            deleteCommunityImgUrls.forEach(awsS3Service::deleteImage);
        }
        // userId에 해당하는 모든 communityBoard 데이터를 삭제
        communityBoardRepository.deleteAll(communityBoards);

        likeHomeHashtags.forEach(likeHomeHashtagService::delete);
        myHomeHashtags.forEach(myHomeHashtagService::delete);
        likeHashtags.forEach(likeHashtagService::delete);
        myHashtags.forEach(myHashtagService::delete);
        if(!Objects.equals(findUser.getProfileUrl(), "https://test-aloha1.s3.ap-northeast-2.amazonaws.com/profile.png")) {
            awsS3Service.deleteImage(findUser.getProfileUrl());
        }
        userRepository.delete(findUser);
        return "회원 탈퇴 완료";
    }
}
