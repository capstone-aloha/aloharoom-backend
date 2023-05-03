package com.aloharoombackend.controller;

import com.aloharoombackend.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;
    //유저 수, 지역별 게시물 수
    @GetMapping
    public ResponseEntity getData() {
        return ResponseEntity.ok(dataService.getData());
    }

    //해당 평수에 대한 월세 분포
    @GetMapping("/{flat}")
    ResponseEntity getRentDistribution(@PathVariable Integer flat) {
        return ResponseEntity.ok(dataService.getRentDistribution(flat));
    }
}
