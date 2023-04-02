package com.aloharoombackend.dto;

import com.aloharoombackend.model.HomeComment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private String nickname;
    private String content;
    //추후 시간 추가

    public CommentDto(HomeComment homeComment) {
        this.nickname = homeComment.getUser().getNickname();
        this.content = homeComment.getContent();
    }

}
