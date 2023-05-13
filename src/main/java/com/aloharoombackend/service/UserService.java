package com.aloharoombackend.service;

import com.aloharoombackend.dto.MyPageDto;
import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.dto.UserInfoDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.CommentRepository;
import com.aloharoombackend.repository.CommunityBoardRepository;
import com.aloharoombackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final LikeProductService likeProductService;
    private final MyProductService myProductService;
    private final LikeHashtagService likeHashtagService;
    private final MyHashtagService myHashtagService;
    private final AwsS3Service awsS3Service;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원 탈퇴 시 순환참조로 인해 선언
    private final CommunityBoardRepository communityBoardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public String signUp(SignUpDto signUpDto) {
        User user = new User(signUpDto);
        user.setRole("ROLE_USER");
        user.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/profile.png");
        String rawPassword = user.getPassword(); //입력받은 pw
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //인코딩한 pw
        user.setPassword(encPassword);

        List<LikeProduct> likeProducts = signUpDto.getLikeProducts()
                .stream().map(likeProduct -> new LikeProduct(likeProduct, user)).collect(Collectors.toList());
        List<MyProduct> myProducts = signUpDto.getMyProducts()
                .stream().map(myProduct -> new MyProduct(myProduct, user)).collect(Collectors.toList());
        List<LikeHashtag> likeHashtags = signUpDto.getLikeHashtags()
                .stream().map(likeHashtag -> new LikeHashtag(likeHashtag, user)).collect(Collectors.toList());
        List<MyHashtag> myHashtags = signUpDto.getMyHashtags()
                .stream().map(myHashtag -> new MyHashtag(myHashtag, user)).collect(Collectors.toList());

        userRepository.save(user);
        likeProductService.create(likeProducts);
        myProductService.create(myProducts);
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

    private void validateDuplicateMember(User user) {
        List<User> findMembers = (List<User>) userRepository.findByUsername(user.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
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
        findUser.getMyProducts().stream().
                forEach(myProduct -> myProduct.getName());
        return findUser;
    }

    //프록시 -> 실객체 생성, 유저 조회 사용
    public User findOneFetchAll(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
        findUser.getMyHashtags().stream().
                forEach(MyHashtag::getHash);
        findUser.getMyProducts().stream().
                forEach(MyProduct::getName);
        findUser.getLikeHashtags().stream().
                forEach(LikeHashtag::getHash);
        findUser.getLikeProducts().stream().
                forEach(LikeProduct::getName);
        return findUser;
    }

    //회원 조회
    public MyPageDto findUser(Long userId) {
        User findUser = findOneFetchAll(userId);
        return new MyPageDto(findUser);
    }

    //회원의 해시태그, 가전제품 조회
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
        String rawPassword = myPageEditDto.getPassword(); //입력받은 pw
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //인코딩한 pw
        myPageEditDto.setPassword(encPassword);

        //삭제
        List<LikeHashtag> likeHashtags = findUser.getLikeHashtags();
        List<LikeProduct> likeProducts = findUser.getLikeProducts();
        List<MyHashtag> myHashtags = findUser.getMyHashtags();
        List<MyProduct> myProducts = findUser.getMyProducts();

        likeProducts.forEach(likeProductService::delete);
        myProducts.forEach(myProductService::delete);
        likeHashtags.forEach(likeHashtagService::delete);
        myHashtags.forEach(myHashtagService::delete);

        //새로 생성
        List<LikeProduct> newLikeProducts = myPageEditDto.getLikeProducts()
                .stream().map(likeProduct -> new LikeProduct(likeProduct, findUser)).collect(Collectors.toList());
        List<MyProduct> newMyProducts = myPageEditDto.getMyProducts()
                .stream().map(myProduct -> new MyProduct(myProduct, findUser)).collect(Collectors.toList());
        List<LikeHashtag> newLikeHashtags = myPageEditDto.getLikeHashtags()
                .stream().map(likeHashtag -> new LikeHashtag(likeHashtag, findUser)).collect(Collectors.toList());
        List<MyHashtag> newMyHashtags = myPageEditDto.getMyHashtags()
                .stream().map(myHashtag -> new MyHashtag(myHashtag, findUser)).collect(Collectors.toList());

        //업데이트
        findUser.edit(myPageEditDto, profileUrl);
        likeProductService.create(newLikeProducts);
        myProductService.create(newMyProducts);
        likeHashtagService.create(newLikeHashtags);
        myHashtagService.create(newMyHashtags);
        return "수정 성공";
    }

    @Transactional
    public String delete(Long userId) {
        User findUser = getUserById(userId);
        List<LikeHashtag> likeHashtags = findUser.getLikeHashtags();
        List<LikeProduct> likeProducts = findUser.getLikeProducts();
        List<MyHashtag> myHashtags = findUser.getMyHashtags();
        List<MyProduct> myProducts = findUser.getMyProducts();

        // userId에 해당하는 모든 communityBoard 데이터를 삭제
        List<CommunityBoard> communityBoards = communityBoardRepository.findAllByUserId(userId);
        for (CommunityBoard communityBoard : communityBoards) {
            Long boardId = communityBoard.getId();
            commentRepository.deleteByCommunityBoardId(boardId);
            List<CommunityImage> communityImages = communityBoard.getCommunityImages();
            List<String> deleteImgUrls = communityImages.stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
            deleteImgUrls.forEach(awsS3Service::deleteImage);
        }
        // userId에 해당하는 모든 communityBoard 데이터를 삭제
        communityBoardRepository.deleteAll(communityBoards);

        likeProducts.forEach(likeProductService::delete);
        myProducts.forEach(myProductService::delete);
        likeHashtags.forEach(likeHashtagService::delete);
        myHashtags.forEach(myHashtagService::delete);
        userRepository.delete(findUser);
        return "회원 탈퇴 완료";
    }
}
