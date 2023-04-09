package com.aloharoombackend.dto;

import com.aloharoombackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserInfoDto {
    private List<String> likeHashtags;
    private List<String> likeProducts;
//    private List<String> myHashtags;
//    private List<String> myProducts;

    public UserInfoDto(User user) {
        this.likeHashtags = user.getLikeHashtags().stream().map(likeHashtag -> likeHashtag.getHash()).collect(Collectors.toList());
        this.likeProducts = user.getLikeProducts().stream().map(likeProduct -> likeProduct.getName()).collect(Collectors.toList());
    }
}
