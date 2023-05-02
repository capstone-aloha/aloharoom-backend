package com.aloharoombackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //초기 데이터
public class CommunityBoardDto {
    public String title;
    public String contents;
    public Integer code;
}
