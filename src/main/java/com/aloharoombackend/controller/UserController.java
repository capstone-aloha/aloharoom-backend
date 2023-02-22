package com.aloharoombackend.controller;

import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.UserRepository;
import com.aloharoombackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
//    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<List<SignUpDto>> signUp(@RequestBody User user){
        //해시태그, 가전제품, 사용자 객체들 따로 만들어서 저장(dto이용)
        System.out.println("user = " + user);
        SignUpDto signUpDto = new SignUpDto();
        List<User> users = userService.findUsers();
        List<LikeProduct> likeProducts = likeProductService.findAll();
        List<LikeHashtag> LikeHashtags = likeHashtagService.findAll();
        List<MyProduct> myProducts = myProductService.findAll();
        List<MyHashtag> myHashtags = myHashtagService.findAll();


        return ResponseEntity.ok().body(signUpDto);
    }

}
