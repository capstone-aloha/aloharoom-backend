package com.aloharoombackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardEditDto {

    private String contents;
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
    private Double x; //위도
    private Double y; //경도
}
