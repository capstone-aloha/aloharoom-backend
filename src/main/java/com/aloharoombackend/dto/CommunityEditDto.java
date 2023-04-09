package com.aloharoombackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityEditDto {
    private String title;
    private String contents;
    private Integer code;
}
