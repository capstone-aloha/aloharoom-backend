package com.aloharoombackend.dto;

import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HeartBoardDto {

    private Long boardId;
    private String title;
    private String address;
    private Integer rent;
    private Integer flat;
    private Integer roomCount;
    private String nickname;
    private String startDate;
    private String imgUrl;
    private Integer commentNum; //댓글 갯수

    public HeartBoardDto(Board board) {
        Home home = board.getHome();
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.address = board.getHome().getAddress();
        this.rent = home.getPrice();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.nickname = board.getUser().getNickname();
        this.startDate = home.getStartDate().toString().replace('-', '.');
        this.imgUrl = board.getHome().getHomeImages().get(0).getImgUrl();
        this.commentNum = board.getHomeComments().size();
    }
}
