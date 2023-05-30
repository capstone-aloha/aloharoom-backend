package com.aloharoombackend.dto;

import com.aloharoombackend.model.Home;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class DataDto {
    //유저 수, 지역별 게시물 수, 평 수랑 월세
    int userTotal; //유저수
    Map<Integer, Integer> userNumIncreaseMap; //월별 사용자 증가
    Map<String, Integer> regionBoardMap; //지역별 게시물 수

    public DataDto(Integer userTotal, Map userNumIncreaseMap,List<Home> homes) {
        this.userTotal = userTotal;
        this.userNumIncreaseMap = userNumIncreaseMap;
        this.regionBoardMap = new HashMap<>();
        regionBoardMap.put("서울", 0);
        regionBoardMap.put("부산", 0);
        regionBoardMap.put("인천", 0);
        regionBoardMap.put("대구", 0);
        regionBoardMap.put("대전", 0);
        regionBoardMap.put("광주", 0);
        regionBoardMap.put("울산", 0);
        homes.stream()
                .forEach(home -> {
                    String region = home.getAddress().substring(0,2); //서울
                    regionBoardMap.put(region, regionBoardMap.get(region) + 1);
                });
    }
}
