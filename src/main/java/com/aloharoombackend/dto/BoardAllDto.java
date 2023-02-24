package com.aloharoombackend.dto;

import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BoardAllDto {

    private Long boardId;
    private String title;
    private String address;
    private Integer price;
    private Integer flat;
    private Integer roomCount;
    private List<String> homeImgUrls;
    private Double x; //위도
    private Double y; //경도


    public BoardAllDto(Board board, Home home) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.address = home.getAddress();
        this.price = home.getPrice();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.homeImgUrls = home.getHomeImages().stream()
                .map(homeImage->homeImage.getImgUrl()).collect(Collectors.toList());
        this.x = home.getX(); //위도
        this.y = home.getY(); //경도
    }
}
