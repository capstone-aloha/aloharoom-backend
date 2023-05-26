package com.aloharoombackend.model;

import com.aloharoombackend.dto.CommunityBoardDto;
import com.aloharoombackend.dto.CommunityEditDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CommunityBoard extends BaseEntity{
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
    private List<Comment> comments;

    @OneToMany(mappedBy = "communityBoard", cascade = CascadeType.ALL)
    private List<CommunityImage> communityImages = new ArrayList<>();

    public CommunityBoard(User user, CommunityBoardDto communityBoardDto) {
        this.user = user;
        this.title = communityBoardDto.getTitle();
        this.contents = communityBoardDto.getContents();
        this.code = communityBoardDto.getCode();
    }

    public CommunityBoard change(CommunityEditDto communityEditDto, List<CommunityImage> communityImages) {
        this.title = communityEditDto.getTitle();
        this.contents = communityEditDto.getContents();
        this.code = communityEditDto.getCode();
        this.communityImages = communityImages;
        return this;
    }

    //조회수 증가
    public CommunityBoard updateViews(Integer views){
        this.views = views + 1;
        return this;
    }
}
