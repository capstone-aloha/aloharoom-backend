package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class HomeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    private String imgUrl;

    public HomeImage(Home home, String imgUrl) {
        home.getHomeImages().add(this);
        this.home = home;
        this.imgUrl = "https://test-aloha1.s3.ap-northeast-2.amazonaws.com/" + imgUrl;
    }
}
