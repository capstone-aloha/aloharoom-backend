package com.aloharoombackend.dto;

import com.aloharoombackend.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserInfoDto {
    private List<String> likeHashtags;
    private List<String> likeHomeHashtags;
    private List<String> myHashtags;
    private List<String> myHomeHashtags;

    public UserInfoDto(User user) {
        this.likeHashtags = user.getLikeHashtags().stream().map(LikeHashtag::getHash).collect(Collectors.toList());
        this.likeHomeHashtags = user.getLikeHomeHashtags().stream().map(LikeHomeHashtag::getName).collect(Collectors.toList());
        this.myHashtags = user.getMyHashtags().stream().map(MyHashtag::getHash).collect(Collectors.toList());
        this.myHomeHashtags = user.getMyHomeHashtags().stream().map(MyHomeHashtag::getName).collect(Collectors.toList());
    }
}
