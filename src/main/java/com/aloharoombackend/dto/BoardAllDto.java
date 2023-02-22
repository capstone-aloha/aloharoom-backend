package com.aloharoombackend.dto;

import com.aloharoombackend.model.Board;
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
    private String price;
    private String flat;
    private int roomCount;
    private List<String> homeImgUrls;
    private Double x; //위도
    private Double y; //경도

    public BoardAllDto (Board board) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.address = board.getHome().getName();
        this.price = board.getHome().getPrice();
        this.flat = board.getHome().getFlat();
        this.roomCount = board.getHome().getRoomCount();
        this.homeImgUrls = board.getHome().getHomeImages().stream()
                .map(homeImage->homeImage.getHomeImgUrl()).collect(Collectors.toList());
        this.x = board.getHome().getLocation().getLatitude(); //위도
        this.y = board.getHome().getLocation().getLongitude(); //경도
    }
}
