package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//@Entity
@Getter
@NoArgsConstructor
public class CommunityBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

//    추천을 누른 사용자
//    private List<Recommend> recommends;

    private Integer code; //커뮤니티 구분

    @Column(name = "views")
    private Integer views = 0;

    @OneToMany(mappedBy = "communityBoard")
    private List<CommunityComment> communityComments;

    @OneToMany(mappedBy = "communityBoard")
    private List<CommunityImage> communityImages;
}
