package com.aloharoombackend.dto;

import com.aloharoombackend.model.LikeHashtag;
import com.aloharoombackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilterDto {

    private String gender;
    private Integer roomCount; //방 갯수
    private String homeType; //아파트, 빌라, 주택
    private List<Integer> ageRange; //원하는 나이 범위
    private List<Integer> flatRange; //원하는 평수 범위
    private List<Integer> rentRange; //원하는 월세 범위
    private List<String> likeHashtags;

    public void setUser(User user) {
        this.likeHashtags = user.getLikeHashtags().stream()
                .map(likeHashtag -> likeHashtag.getHash()).collect(Collectors.toList());
    }

    //test용
    public SearchFilterDto(Integer minAge, Integer maxAge, String gender, List<String> likeHashtags, Integer minFlat, Integer maxFlat,
                           Integer minRent, Integer maxRent, Integer roomCount, String homeType) {
        this.ageRange = Arrays.asList(minAge, maxAge);
        this.gender = gender;
        this.likeHashtags = likeHashtags;
        this.flatRange = Arrays.asList(minFlat, maxFlat);
        this.rentRange = Arrays.asList(minRent, maxRent);
        this.roomCount = roomCount;
        this.homeType = homeType;
    }
}
