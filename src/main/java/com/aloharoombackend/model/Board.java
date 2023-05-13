package com.aloharoombackend.model;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardEditDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter //삭제해야함
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    private Integer minAge;

    private Integer maxAge;

    private String openChatUrl;

    @Column(name = "contents")
    private String contents;

    @Column(name = "views")
    private Integer views = 0;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    private Boolean activation; //활성화

    public Board(Home home, User user, BoardAddDto boardAddDto) {
        this.home = home;
        this.contents = boardAddDto.getContents();
        this.user = user;
        this.minAge = boardAddDto.getAgeRange().get(0);
        this.maxAge = boardAddDto.getAgeRange().get(1);
        this.openChatUrl = boardAddDto.getOpenChatUrl();
        this.activation = true;
    }

    public Board change(BoardEditDto boardEditDto) {
        this.contents = boardEditDto.getContents();
        List<Integer> ageRange = boardEditDto.getAgeRange();
        this.minAge = ageRange.get(0);
        this.maxAge = ageRange.get(1);
        return this;
    }

    public void deactivate() {
        this.activation = false;
    }

    public void activate() {
        this.activation = true;
    }
}
