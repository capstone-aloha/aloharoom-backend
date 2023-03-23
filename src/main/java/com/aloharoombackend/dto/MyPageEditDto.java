package com.aloharoombackend.dto;

import com.aloharoombackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageEditDto {

    private String password;
    private String nickname;
    private Integer age;
    private String gender;
    private String tendency;
    private String profileUrl;
    private List<String> likeHashtags;     //likeHash
    private List<String> likeProducts;    //likeProduct
    private List<String> myHashtags;    //myHash
    private List<String> myProducts;    //myProduct

    public MyPageEditDto(User user) {
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.tendency = user.getTendency();
        this.profileUrl = user.getProfileUrl();
        this.likeHashtags = user.getLikeHashtags().stream().map(likeHashtag -> likeHashtag.getHash()).collect(Collectors.toList());
        this.likeProducts = user.getLikeProducts().stream().map(likeProduct -> likeProduct.getName()).collect(Collectors.toList());
        this.myHashtags = user.getMyHashtags().stream().map(myHashtag -> myHashtag.getHash()).collect(Collectors.toList());
        this.myProducts = user.getMyProducts().stream().map(myProduct -> myProduct.getName()).collect(Collectors.toList());
    }

}
