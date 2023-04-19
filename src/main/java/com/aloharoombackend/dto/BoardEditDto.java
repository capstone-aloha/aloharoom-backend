package com.aloharoombackend.dto;

import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
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
    private Double x;
    private Double y;

    public BoardEditDto(Board board, Home home) {
        this.contents = board.getContents();
        this.roomCount = home.getRoomCount();
        this.address = home.getAddress();
        this.homeType = home.getHomeType();
        this.tradeType = home.getTradeType();
        this.price = home.getPrice();
        this.deposit = home.getDeposit();
        this.rent = home.getPrice();
        this.flat = home.getFlat();
        this.maintenance = home.getMaintenance();
        this.floor = home.getFloor();
        this.totalFloor = home.getTotalFloor();
        this.startDate = home.getStartDate();
    }
}
