package com.aloharoombackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardAddDto {
    private String title;
    private String contents;
    private Integer count; //구하는 인원
    private Integer roomCount;
    private String address;
    private String homeType;
    private String tradeType;
    private Integer price;
    private Integer deposit;
    private Integer rent;
    private Integer flat;
    private Integer maintenance;
    private Integer floor;
    private Integer totalFloor;
    private LocalDate startDate;
    //일단 보류
    //private String transportations;
    //private String facilities;
    private Double x;
    private Double y;

}
