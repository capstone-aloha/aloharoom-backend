package com.aloharoombackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityBoardDto {
    public String title;
    public String contents;
    public Integer code;
}
