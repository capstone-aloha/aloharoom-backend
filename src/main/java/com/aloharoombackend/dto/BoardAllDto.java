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
    private Integer rent;
    private Integer flat;
    private Integer roomCount;
    private String nickname;
    private String startDate;
    private List<String> imgUrls;
    private Double x; //위도
    private Double y; //경도
    private Integer commentNum; //댓글 갯수

    public BoardAllDto(Board board, Home home) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.address = home.getAddress();
        this.rent = home.getPrice();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.nickname = board.getUser().getNickname();
        this.startDate = home.getStartDate().toString();
        this.imgUrls = home.getHomeImages().stream()
                .map(homeImage->homeImage.getImgUrl()).collect(Collectors.toList());
        this.x = home.getX(); //위도
        this.y = home.getY(); //경도
        this.commentNum = board.getComments().size();
    }
}
