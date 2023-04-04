package com.aloharoombackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditCommentDto {
    private Long homeCommentId;
    private String content;
}
