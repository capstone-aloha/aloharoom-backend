package com.aloharoombackend.controller;

import com.aloharoombackend.dto.MyPageDto;
import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LikeProductService likeProductService;
    private final MyProductService myProductService;
    private final LikeHashtagService likeHashtagService;
    private final MyHashtagService myHashtagService;

    //회원 가입
    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpDto signUpDto){
        //해시태그, 가전제품, 사용자 객체들 따로 만들어서 저장(dto이용)
        User user = new User(signUpDto);

        List<LikeProduct> likeProducts = signUpDto.getLikeProducts()
                .stream().map(likeProduct -> new LikeProduct(likeProduct, user)).collect(Collectors.toList());
        List<MyProduct> myProducts = signUpDto.getMyProducts()
                .stream().map(myProduct -> new MyProduct(myProduct, user)).collect(Collectors.toList());
        List<LikeHashtag> likeHashtags = signUpDto.getLikeHashtags()
                .stream().map(likeHashtag -> new LikeHashtag(likeHashtag, user)).collect(Collectors.toList());
        List<MyHashtag> myHashtags = signUpDto.getMyHashtags()
                .stream().map(myHashtag -> new MyHashtag(myHashtag, user)).collect(Collectors.toList());

        userService.join(user);
        likeProductService.create(likeProducts);
        myProductService.create(myProducts);
        likeHashtagService.create(likeHashtags);
        myHashtagService.create(myHashtags);

        return user;
    }

    //회원 조회
    @GetMapping("/myPage/{userId}")
    public MyPageDto myPage(@PathVariable Long userId) {
        User findUser = userService.findOneFetchAll(userId);
        MyPageDto findUserDto = new MyPageDto(findUser);
        return findUserDto;
    }

    //회원 수정
    @PatchMapping("/myPage/{userId}")
    public MyPageEditDto myPageEdit(@RequestPart(value = "myPageEditDto") MyPageEditDto myPageEditDto,
                                    @PathVariable Long userId) {
        User findUser = userService.findOneFetchAll(userId); //프록시 -> 실객체
        List<LikeHashtag> likeHashtags = findUser.getLikeHashtags();
        List<LikeProduct> likeProducts = findUser.getLikeProducts();
        List<MyHashtag> myHashtags = findUser.getMyHashtags();
        List<MyProduct> myProducts = findUser.getMyProducts();

        //삭제
        likeHashtags.forEach(likeHashtagService::delete);
        likeProducts.forEach(likeProductService::delete);
        myHashtags.forEach(myHashtagService::delete);
        myProducts.forEach(myProductService::delete);

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
        userService.update(userId, myPageEditDto);
        likeProductService.create(newLikeProducts);
        myProductService.create(newMyProducts);
        likeHashtagService.create(newLikeHashtags);
        myHashtagService.create(newMyHashtags);

        return myPageEditDto;
    }




}















