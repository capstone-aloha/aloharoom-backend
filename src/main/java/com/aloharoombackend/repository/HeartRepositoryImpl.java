package com.aloharoombackend.repository;

import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.model.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.aloharoombackend.model.QBoard.board;
import static com.aloharoombackend.model.QHeart.heart;
import static com.aloharoombackend.model.QHome.home;
import static com.querydsl.jpa.JPAExpressions.select;

public class HeartRepositoryImpl implements HeartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HeartRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<HeartBoardDto> findByHeartBoard(Long userId) {
        //distinct 써야함.
        List<Board> boards = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.home, home).fetchJoin()
                .join(home.homeImages).fetchJoin()
                .where(board.id.in(
                        select(heart.boardId)
                                .from(heart)
                                .where(heart.userId.eq(userId))
                )).fetch();

        return boards.stream().map(board -> new HeartBoardDto(board)).collect(Collectors.toList());
    }
}
