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
    private Long userId;
    private String contents;
    private String address;
    private Integer roomCount;
    private String homeType;
    private String tradeType;
    private Integer flat;
    private Integer floor;
    private Integer totalFloor;
    private String price;
    private Integer deposit;
    private Integer maintenance;
    private Integer rent;
    private LocalDate startDate;
    private List<String> imgUrls;
    private Double x;
    private Double y;
    private String nickname;
    private String profileImgUrl;
    private String openChatUrl;
    private Integer age;
    private String gender;
    private List<String> myHashtag;
    private List<String> myHomeHashtag;
    private String preferAgeRange; //선호하는 연령층
    private Boolean isHeart; //좋아요 눌렀는 지 판단

    public BoardOneDto(Board board, Home home, User user, Boolean isHeart) {
        this.userId = user.getId();
        this.contents = board.getContents();
        this.address = home.getAddress();
        this.maintenance = home.getMaintenance();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.homeType = home.getHomeType();
        this.tradeType = home.getTradeType();
        Integer price = home.getPrice();
        Integer deposit = home.getDeposit();
        if(deposit != null) {
            this.price = deposit + "/" + price;
        } else {
            if(price<10000) this.price = price.toString();
            else {
                int s = price / 10000;
                int e = price % 10000;
                if(e==0) this.price = s + "억";
                else this.price = s + "억" + e;
            }
        }
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
        this.openChatUrl = board.getOpenChatUrl();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.myHashtag = user.getMyHashtags().stream()
                .map(myHashtag -> myHashtag.getHash()).collect(Collectors.toList());
        this.myHomeHashtag = user.getMyHomeHashtags().stream()
                .map(myHomeHashtag -> myHomeHashtag.getName()).collect(Collectors.toList());
        if(board.getMinAge() == board.getMaxAge())
            this.preferAgeRange = board.getMinAge().toString();
        else this.preferAgeRange = board.getMinAge() + " ~ " + board.getMaxAge();
        this.isHeart = isHeart;
    }

    public BoardOneDto(Board board, Home home) {
        this.contents = board.getContents();
        this.address = home.getAddress();
        this.maintenance = home.getMaintenance();
        this.flat = home.getFlat();
        this.roomCount = home.getRoomCount();
        this.homeType = home.getHomeType();
        this.tradeType = home.getTradeType();
        this.price = home.getPrice().toString();
        this.deposit = home.getDeposit();
        this.floor = home.getFloor();
        this.totalFloor = home.getTotalFloor();
        this.startDate = home.getStartDate();
        this.imgUrls = home.getHomeImages().stream()
                .map(homeImage -> homeImage.getImgUrl()).collect(Collectors.toList());
    }
}
