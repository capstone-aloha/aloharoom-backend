package com.aloharoombackend.model;

import com.aloharoombackend.dto.AddHeartDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@IdClass(HeartId.class)
public class Heart {
    @Id
    private Long boardId;

    @Id
    private Long userId;

    public Heart(Long boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }

    public Heart(AddHeartDto addLikeDto) {
        this.boardId = addLikeDto.getBoardId();
        this.userId = addLikeDto.getUserId();
    }
}
