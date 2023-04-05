package com.aloharoombackend.dto;

import com.aloharoombackend.model.HomeComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long homeCommentId;
    private Long userId;
    private String nickname;
    private String content;
    private LocalDateTime createdDate;
    private List<CommentDto> commentDtos = new ArrayList<>();
    //추후 시간 추가

    public CommentDto(HomeComment homeComment) {
        this.homeCommentId = homeComment.getId();
        this.userId = homeComment.getUser().getId();
        this.nickname = homeComment.getUser().getNickname();
        this.content = homeComment.getContent();
        this.createdDate = homeComment.getCreatedDate();
    }

}
