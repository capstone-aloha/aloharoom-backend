package com.aloharoombackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class HeartId implements Serializable {
    private Long boardId;

    private Long userId;

    public HeartId(Long boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }
}
