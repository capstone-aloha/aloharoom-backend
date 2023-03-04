package com.aloharoombackend.dto;

import com.aloharoombackend.model.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeartBoardDto {

    private String title;
    private String address;
    private String imgUrl;

    public HeartBoardDto(Board board) {
        this.title = board.getTitle();
        this.address = board.getHome().getAddress();
        this.imgUrl = board.getHome().getHomeImages().get(0).getImgUrl();
    }
}
