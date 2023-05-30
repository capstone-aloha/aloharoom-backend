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

    private String nickname;
    private Integer age;
    private String gender;
    private String profileUrl;
    private List<String> likeHashtags;     //likeHash
    private List<String> likeHomeHashtags;    //likeProduct
    private List<String> myHashtags;    //myHash
    private List<String> myHomeHashtags;    //myProduct

    public MyPageEditDto(User user) {
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.profileUrl = user.getProfileUrl();
        this.likeHashtags = user.getLikeHashtags().stream().map(likeHashtag -> likeHashtag.getHash()).collect(Collectors.toList());
        this.likeHomeHashtags = user.getLikeHomeHashtags().stream().map(likeHomeHashtag -> likeHomeHashtag.getName()).collect(Collectors.toList());
        this.myHashtags = user.getMyHashtags().stream().map(myHashtag -> myHashtag.getHash()).collect(Collectors.toList());
        this.myHomeHashtags = user.getMyHomeHashtags().stream().map(myHomeHashtag -> myHomeHashtag.getName()).collect(Collectors.toList());
    }

}
