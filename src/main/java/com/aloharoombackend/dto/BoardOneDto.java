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
    private String contents;
    private String address;
    private Integer roomCount;
    private String homeType;
    private String tradeType;
    private Integer flat;
    private Integer floor;
    private Integer totalFloor;
    private Integer price;
    private Integer deposit;
    private Integer maintenance;
    private Integer rent;
    private LocalDate startDate;
    private List<String> imgUrls;
    private Double x;
    private Double y;
    private String nickname;
    private String profileImgUrl;
    private Integer age;
    private String gender;
    private List<String> hashtag;
    private List<String> product;
    private String preferAgeRange; //선호하는 연령층

    public BoardOneDto(Board board, Home home, User user) {
        this.contents = board.getContents();
        this.address = home.getAddress();
        this.maintenance = home.getMaintenance();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.homeType = home.getHomeType();
        this.tradeType = home.getTradeType();
        this.price = home.getPrice();
        this.deposit = home.getDeposit();
        this.rent = home.getRent();
        this.floor = home.getFloor();
        this.totalFloor = home.getTotalFloor();
        this.startDate = home.getStartDate();
        this.imgUrls = home.getHomeImages().stream()
                .map(homeImage -> homeImage.getImgUrl()).collect(Collectors.toList());
        this.x = home.getX();
        this.y = home.getY();
        this.nickname = user.getNickname();
        this.profileImgUrl = user.getProfileUrl();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.hashtag = user.getMyHashtags().stream()
                .map(myHashtag -> myHashtag.getHash()).collect(Collectors.toList());
        this.product = user.getMyProducts().stream()
                .map(myProduct -> myProduct.getName()).collect(Collectors.toList());
        if(board.getMinAge() == board.getMaxAge())
            this.preferAgeRange = board.getMinAge().toString();
        else this.preferAgeRange = board.getMinAge() + " ~ " + board.getMaxAge();
    }

    public BoardOneDto(Board board, Home home) {
        this.contents = board.getContents();
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
    }
}
