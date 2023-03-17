package com.aloharoombackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RecentViewId implements Serializable {
    private Long boardId;

    private Long userId;

    public RecentViewId(Long boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }
}
