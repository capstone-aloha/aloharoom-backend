package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Notification extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_board_id")
    private CommunityBoard communityBoard;

    private String content;

    private Boolean isCheck;

    public Notification(User user, Board board, String content) {
        this.user = user;
        this.board = board;
        this.content = content;
        this.isCheck = false;
    }
    public Notification(User user, CommunityBoard communityBoard, String content) {
        this.user = user;
        this.communityBoard = communityBoard;
        this.content = content;
        this.isCheck = false;
    }
    
    public void check() {
        this.isCheck = true;
    }
}
