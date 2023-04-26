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
    private String address;
    private Integer rent;
    private Integer flat;
    private Integer roomCount;
    private String homeType;
    private String nickname;
    private String profileImgUrl;
    private String startDate;
    private String imgUrl;
    private Double x; //위도
    private Double y; //경도
    private Integer commentNum; //댓글 갯수


    public BoardAllDto(Board board, Home home) {
        this.boardId = board.getId();
        this.address = home.getAddress();
        this.rent = home.getRent();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.homeType = home.getHomeType();
        this.nickname = board.getUser().getNickname();
        this.profileImgUrl = board.getUser().getProfileUrl();
        this.startDate = home.getStartDate().toString();
        this.imgUrl = home.getHomeImages().stream()
                .map(homeImage->homeImage.getImgUrl()).collect(Collectors.toList()).get(0);
        this.x = home.getX(); //위도
        this.y = home.getY(); //경도
        this.commentNum = board.getComments().size();
    }
}
