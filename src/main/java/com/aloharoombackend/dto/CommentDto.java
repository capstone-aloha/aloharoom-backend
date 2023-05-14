package com.aloharoombackend.dto;

import com.aloharoombackend.model.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private Long userId;
    private String profileUrl;
    private String nickname;
    private String content;
    private String createdDate;
    private List<CommentDto> commentDtos = new ArrayList<>();

    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.userId = comment.getUser().getId();
        this.profileUrl = comment.getUser().getProfileUrl();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        //2023.04.05 16:23
        this.createdDate = comment.getCreatedDate().toString().replace('-', '.').replace('T', ' ').substring(0, 16);
    }

}
