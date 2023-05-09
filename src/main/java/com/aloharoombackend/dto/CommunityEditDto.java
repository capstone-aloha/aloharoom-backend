package com.aloharoombackend.dto;

import com.aloharoombackend.model.CommunityBoard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityEditDto {
    private Long userId;
    private String nickname;
    private String title;
    private String contents;
    private Integer code;

    public CommunityEditDto(CommunityBoard communityBoard) {
        this.userId = communityBoard.getUser().getId();
        this.nickname = communityBoard.getUser().getNickname();
        this.title = communityBoard.getTitle();
        this.contents = communityBoard.getContents();
        this.code = communityBoard.getCode();
    }
}
