package com.aloharoombackend.controller;

import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LikeProductService likeProductService;
    private final MyProductService myProductService;
    private final LikeHashtagService likeHashtagService;
    private final MyHashtagService myHashtagService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpDto signUpDto){
        //해시태그, 가전제품, 사용자 객체들 따로 만들어서 저장(dto이용)
//        SignUpDto signUpDto = new SignUpDto();
        List<LikeProduct> likeProducts = signUpDto.getLikeProducts()
                .stream().map(likeProduct -> new LikeProduct(likeProduct)).collect(Collectors.toList());
        List<MyProduct> myProducts = signUpDto.getMyProducts()
                .stream().map(myProduct -> new MyProduct(myProduct)).collect(Collectors.toList());
        List<LikeHashtag> likeHashtags = signUpDto.getLikeHashtags()
                .stream().map(likeHashtag -> new LikeHashtag(likeHashtag)).collect(Collectors.toList());
        List<MyHashtag> myHashtags = signUpDto.getMyHashtags()
                .stream().map(myHashtag -> new MyHashtag(myHashtag)).collect(Collectors.toList());
        User user = new User(signUpDto, likeProducts, myProducts, likeHashtags, myHashtags);

        return user;
    }

}
