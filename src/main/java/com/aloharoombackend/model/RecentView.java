package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@NoArgsConstructor
@IdClass(RecentViewId.class)
public class RecentView extends BaseEntity{
    @Id
    private Long boardId;

    @Id
    private Long userId;

    public RecentView(Long boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }
}
