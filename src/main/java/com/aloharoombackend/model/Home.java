package com.aloharoombackend.model;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardEditDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "home")
    private List<HomeImage> homeImages = new ArrayList<>();

    private Integer maintenance; //관리비

    private Integer flat; //평수

    private Integer roomCount; //방 갯수
    
    private String homeType; //주거공간 형태 => 아파트, 빌라, 주택

    private String tradeType;

    private Integer price; //매매가, 전세가, 월세 => 20000, 1500, 1500/30 (만원단위)

    private Integer deposit; //매매가, 전세가, 월세 => 20000, 1500, 1500/30 (만원단위)

    private Integer floor; //층수

    private Integer totalFloor; //전체 층수

    private LocalDate startDate; //입주 가능한 날짜

    //Option으로 나중에 빼도될 듯
    private Boolean sink; //싱크대 유무

    private Boolean aircon; //에어컨 유무

    private Boolean washMachine; //세탁기 유무

    private Boolean shoeCloset; //신발장 유무

    private Double x;

    private Double y;

    public Home(BoardAddDto boardAddDto) {
        this.address = boardAddDto.getAddress();
        this.maintenance = boardAddDto.getMaintenance();
        this.flat = boardAddDto.getFlat();
        this.roomCount = boardAddDto.getRoomCount();
        this.homeType = boardAddDto.getHomeType();
        this.tradeType = boardAddDto.getTradeType();
        this.price = boardAddDto.getPrice();
        this.deposit = boardAddDto.getDeposit();
        this.floor = boardAddDto.getFloor();
        this.totalFloor = boardAddDto.getTotalFloor();
        this.startDate = boardAddDto.getStartDate();
        this.x = boardAddDto.getX();
        this.y = boardAddDto.getY();
    }

    public Home change(BoardEditDto boardEditDto, List<HomeImage> homeImages) {
        this.roomCount = boardEditDto.getRoomCount();
        this.address = boardEditDto.getAddress();
        this.homeType = boardEditDto.getHomeType();
        this.tradeType = boardEditDto.getTradeType();
        this.price = boardEditDto.getPrice();
        this.deposit = boardEditDto.getDeposit();
        this.flat = boardEditDto.getFlat();
        this.maintenance = boardEditDto.getMaintenance();
        this.floor = boardEditDto.getFloor();
        this.totalFloor = boardEditDto.getTotalFloor();
        this.startDate = boardEditDto.getStartDate();
        this.homeImages = homeImages;
        return this;
    }
}
