package com.aloharoombackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //test용
@NoArgsConstructor
public class AddCommentDto {
    private Long userId;
    private Long targetUserId; //알림 용도
    private Long boardId;
    private String content;
    private String targetContent; //대댓글 용도
    private Integer layer;
    private Long groupId;
}
