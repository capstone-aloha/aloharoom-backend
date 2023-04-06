package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommunityImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_board_id")
    private CommunityBoard communityBoard;

    private String imgUrl;

    public CommunityImage(String imgUrl, CommunityBoard communityBoard) {
        this.imgUrl = "https://test-aloha1.s3.ap-northeast-2.amazonaws.com/" + imgUrl;
        this.communityBoard = communityBoard;
    }

    public CommunityImage(CommunityBoard communityBoard, String imgUrl) {
        this.communityBoard = communityBoard;
        this.imgUrl = "https://test-aloha1.s3.ap-northeast-2.amazonaws.com/" + imgUrl;
    }
}
