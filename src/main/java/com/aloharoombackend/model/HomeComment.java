package com.aloharoombackend.model;

import com.aloharoombackend.dto.AddCommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class HomeComment extends BaseEntity{
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

    @NonNull
    @Column(name = "content")
    private String content;

    private Integer layer;

    private Long groupId;

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setContent(String content) { this.content = content; }

    public HomeComment(User user, Board board, AddCommentDto addCommentDto) {
        this.user = user;
        this.board = board;
        this.content = addCommentDto.getContent();
        this.layer = addCommentDto.getLayer();
        this.groupId = addCommentDto.getGroupId();
    }
    public HomeComment(User user, CommunityBoard communityBoard, AddCommentDto addCommentDto) {
        this.user = user;
        this.communityBoard = communityBoard;
        this.content = addCommentDto.getContent();
        this.layer = addCommentDto.getLayer();
        this.groupId = addCommentDto.getGroupId();
    }
}
