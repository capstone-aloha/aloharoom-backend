package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long maintenance; //관리비

    private String type; //주거공간 형태 => 아파트, 빌라, 주택

    private String price; //매매가, 전세가, 월세 => 20000, 1500, 1500/30 (만원단위)

    private Long floor; //층수

    private Long totalFloor; //전체 층수

    private LocalDate startDate; //입주 가능한 날짜

    //Option으로 나중에 빼도될 듯
    private Boolean sink; //싱크대 유무

    private Boolean aircon; //에어컨 유무

    private Boolean washMachine; //세탁기 유무

    private Boolean shoeCloset; //신발장 유무
}
