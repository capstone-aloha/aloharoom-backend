package com.aloharoombackend.model;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardEditDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "board", fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "count")
    private Integer count;

    @Column(name = "views")
    private Integer views = 0;

    public Board(Home home, BoardAddDto boardAddDto) {
        this.home = home; //나중에 연관관계 메서드
        this.title = boardAddDto.getTitle();
        this.contents = boardAddDto.getContents();
        this.count = boardAddDto.getCount();
    }

    public Board change(BoardEditDto boardEditDto) {
        this.title = boardEditDto.getTitle();
        this.contents = boardEditDto.getContents();
        this.count = boardEditDto.getCount();
        return this;
    }
}
