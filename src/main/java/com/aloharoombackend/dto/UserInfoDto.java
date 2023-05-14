package com.aloharoombackend.dto;

import com.aloharoombackend.model.MyHashtag;
import com.aloharoombackend.model.MyHomeHashtag;
import com.aloharoombackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserInfoDto {
//    private List<String> likeHashtags;
//    private List<String> likeProducts;
    private List<String> myHashtags;
    private List<String> myHomeHashtags;

    public UserInfoDto(User user) {
        this.myHashtags = user.getMyHashtags().stream().map(MyHashtag::getHash).collect(Collectors.toList());
        this.myHomeHashtags = user.getMyHomeHashtags().stream().map(MyHomeHashtag::getName).collect(Collectors.toList());
    }
}
