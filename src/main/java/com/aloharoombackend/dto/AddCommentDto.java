package com.aloharoombackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCommentDto {
    private Long userId;
    private Long boardId;
    private String content;
    private Integer layer;
    private Long groupId;
}
