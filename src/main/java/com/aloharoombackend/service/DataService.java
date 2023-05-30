package com.aloharoombackend.service;

import com.aloharoombackend.dto.DataDto;
import com.aloharoombackend.dto.RentDistributionDto;
import com.aloharoombackend.model.Home;
import com.aloharoombackend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataService {

    private final UserService userService;
    private final HomeService homeService;

    //유저 수, 지역별 게시물 수 (서울, 부산, 인천, 대구, 대전, 광주, 울산)
    public DataDto getData() {
        List<User> users = userService.findAll();
        Map<Integer, Integer> userNumIncreaseMap = new HashMap<>();
        for (int i = 1; i <= 12; i++) userNumIncreaseMap.put(i, 0);
        for (User user : users) {
            LocalDateTime createdDate = user.getCreatedDate();
            int month = createdDate.getMonth().getValue();
            userNumIncreaseMap.put(month, userNumIncreaseMap.get(month) + 1);
        }

        Integer userTotal = users.size();
        List<Home> homes = homeService.findAll();
        return new DataDto(userTotal, userNumIncreaseMap, homes);
    }

    //해당 평수에 대한 월세 분포
    public RentDistributionDto getRentDistribution(Integer flat) {
        List<Home> homeAll = homeService.findAll();
        List<Home> homes = homeAll.stream().filter(home -> home.getFlat().equals(flat)).collect(Collectors.toList());
        return new RentDistributionDto(homes);
    }
}
