package com.aloharoombackend.dto;

import com.aloharoombackend.model.LikeHashtag;
import com.aloharoombackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchFilterDto {

    private Integer age;

    private String gender;

    private Integer flat; //평수

    private Integer roomCount; //방 갯수

    private String homeType; //아파트, 빌라, 주택

    private Integer rent; //월 가격

    private List<LikeHashtag> likeHashtags;

    public void setUser(User user) {
        this.likeHashtags = user.getLikeHashtags();
    }
}
