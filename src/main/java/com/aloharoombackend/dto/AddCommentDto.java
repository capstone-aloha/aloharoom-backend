package com.aloharoombackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //test용
@NoArgsConstructor
public class AddCommentDto {
    private Long userId;
    private Long targetUserId; //대댓글 달 때만 유효
    private Long boardId;
    private String content;
    private Integer layer;
    private Long groupId;
}
