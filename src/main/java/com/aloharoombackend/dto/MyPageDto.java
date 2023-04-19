package com.aloharoombackend.dto;

import com.aloharoombackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MyPageDto {

    //user
    private Long signupId;
    private String username;
    private String password;
    private String nickname;
    //private String name;
    private Integer age;
    private String gender;
    private String role;
    private String profileUrl;

    //likeHash
    private List<String> likeHashtags;

    //likeProduct
    private List<String> likeProducts;

    //myHash
    private List<String> myHashtags;

    //myProduct
    private List<String> myProducts;

    public MyPageDto(User user) {
        this.signupId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.profileUrl = user.getProfileUrl();
        this.likeHashtags = user.getLikeHashtags().stream().map(likeHashtag -> likeHashtag.getHash()).collect(Collectors.toList());
        this.likeProducts = user.getLikeProducts().stream().map(likeProduct -> likeProduct.getName()).collect(Collectors.toList());
        this.myHashtags = user.getMyHashtags().stream().map(myHashtag -> myHashtag.getHash()).collect(Collectors.toList());
        this.myProducts = user.getMyProducts().stream().map(myProduct -> myProduct.getName()).collect(Collectors.toList());
    }
}
