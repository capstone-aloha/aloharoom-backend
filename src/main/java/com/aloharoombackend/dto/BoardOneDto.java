package com.aloharoombackend.dto;

import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import com.aloharoombackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BoardOneDto {
    private String title;
    private String contents;
    private Integer count; //구하는 인원
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
    private List<String> imgUrls;
    //일단 보류
    //private String transportations;
    //private String facilities;
    //글쓴이, 해시태그, 가전제품
    private String nickname;
    private String tendency;
    private List<String> hashtag;
    private List<String> product;

    public BoardOneDto(Board board, Home home, User user) {
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.count = board.getCount();
        this.address = home.getAddress();
        this.maintenance = home.getMaintenance();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.homeType = home.getHomeType();
        this.tradeType = home.getTradeType();
        this.price = home.getPrice();
        this.deposit = home.getDeposit();
        this.floor = home.getFloor();
        this.totalFloor = home.getTotalFloor();
        this.startDate = home.getStartDate();
        this.imgUrls = home.getHomeImages().stream()
                .map(homeImage -> homeImage.getImgUrl()).collect(Collectors.toList());
        this.nickname = user.getUsername();
        this.tendency = user.getTendency();
        //가전제품은 해봤자 4개 정도니까 일대다로 푸는게 날듯.. => 일단 User, MyProduct만 수정, 딴 곳 오류터진부분 수정해야함
        //User: Product, Hashtag fetch join하도록 override => 나중에 쓰려면 필요
        this.hashtag = user.getMyHashtags().stream()
                .map(myHashtag -> myHashtag.getHash()).collect(Collectors.toList());
        this.product = user.getMyProducts().stream()
                .map(myProduct -> myProduct.getName()).collect(Collectors.toList());
        //해시태그를 어떻게 보내야할까
    }
}
