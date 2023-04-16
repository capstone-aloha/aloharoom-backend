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
    private Long communityId;
    private String title;
    private String contents;
    private Integer code;
    private List<String> imgUrls;

    public CommunityAllDto(CommunityBoard communityBoard) {
        this.communityId = communityBoard.getId();
        this.title = communityBoard.getTitle();
        this.contents = communityBoard.getContents();
        this.code = communityBoard.getCode();
        this.imgUrls = communityBoard.getCommunityImages().stream().map(CommunityImage::getImgUrl).collect(Collectors.toList());
    }
}