package com.aloharoombackend.dto;

import com.aloharoombackend.model.CommunityBoard;
import com.aloharoombackend.model.CommunityImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CommunityAllDto {
    private Long userId;
    private String nickname;
    private String profile;
    private Long communityId;
    private String title;
    private String contents;
    private Integer views;
    private Integer code;
    private List<String> imgUrls;
    private Integer commentNum; //댓글 갯수

    public CommunityAllDto(CommunityBoard communityBoard) {
        this.userId = communityBoard.getUser().getId();
        this.nickname = communityBoard.getUser().getNickname();
        this.profile = communityBoard.getUser().getProfileUrl();
        this.communityId = communityBoard.getId();
        this.title = communityBoard.getTitle();
        this.contents = communityBoard.getContents();
        this.views = communityBoard.getViews();
        this.code = communityBoard.getCode();
        this.imgUrls = communityBoard.getCommunityImages().stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
        this.commentNum = communityBoard.getComments().size();
    }
}
