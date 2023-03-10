package com.aloharoombackend.dto;

import com.aloharoombackend.model.LikeHashtag;
import com.aloharoombackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilterDto {

    private Integer minAge;
    private Integer maxAge;

    private String gender;

    private Integer minFlat; //최소 평수
    private Integer maxFlat; //최대 평수

    private Integer roomCount; //방 갯수

    private String homeType; //아파트, 빌라, 주택

    private Integer minRent; //최소 월 가격
    private Integer maxRent; //최대 월 가격

    private List<String> likeHashtags;

    public void setUser(User user) {
        this.likeHashtags = user.getLikeHashtags().stream()
                .map(likeHashtag -> likeHashtag.getHash()).collect(Collectors.toList());
    }

    //test용
    public SearchFilterDto(Integer minAge, Integer maxAge, String gender, List<String> likeHashtags, Integer minFlat, Integer maxFlat,
                           Integer minRent, Integer maxRent, Integer roomCount, String homeType) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.gender = gender;
        this.likeHashtags = likeHashtags;
        this.minFlat = minFlat;
        this.maxFlat = maxFlat;
        this.minRent = minRent;
        this.maxRent = maxRent;
        this.roomCount = roomCount;
        this.homeType = homeType;
    }
}
