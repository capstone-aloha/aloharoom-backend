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
        //System.out.println("user = " + user);
//        SignUpDto signUpDto = new SignUpDto();
        LikeProduct likeProduct = new LikeProduct(signUpDto);
        List<MyProduct> myProducts = signUpDto.getMyProducts()
                .stream().map(myProduct -> new MyProduct(myProduct)).collect(Collectors.toList());
        LikeHashtag likeHashtag = new LikeHashtag(signUpDto);
        MyHashtag myHashtag = new MyHashtag(signUpDto);
        User user = new User(signUpDto, likeProduct, myProducts, likeHashtag, myHashtag);

        return user;
    }

}
