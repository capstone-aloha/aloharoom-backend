package com.aloharoombackend.service;

import com.aloharoombackend.dto.DataDto;
import com.aloharoombackend.dto.RentDistributionDto;
import com.aloharoombackend.model.Home;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataService {

    private final UserService userService;
    private final HomeService homeService;

    //유저 수, 지역별 게시물 수 (서울, 부산, 인천, 대구, 대전, 광주, 울산)
    public DataDto getData() {
        Integer userTotal = userService.findAll().size();
        List<Home> homes = homeService.findAll();
        return new DataDto(userTotal, homes);
    }

    //해당 평수에 대한 월세 분포
    public RentDistributionDto getRentDistribution(Integer flat) {
        List<Home> homeAll = homeService.findAll();
        List<Home> homes = homeAll.stream().filter(home -> home.getFlat().equals(flat)).collect(Collectors.toList());
        return new RentDistributionDto(homes);
    }
}
