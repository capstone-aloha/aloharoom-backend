package com.aloharoombackend.repository;

import com.aloharoombackend.model.RecentView;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.aloharoombackend.model.QRecentView.recentView;

@Repository
public class RecentViewRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    public RecentViewRepository(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    public void save(RecentView recentView1) {
        em.persist(recentView1);
        em.flush(); em.clear();
    }

    public List<RecentView> findByUserId(Long userId) {
        return queryFactory
                .selectFrom(recentView)
                .where(recentView.userId.eq(userId))
                .fetch();
    }

    //서브쿼리로 오래된 날짜 + 해당 userId 조건줘서 날짜 구하고, 메인쿼리의 where절에서 in 쿼리로 받아서 RecentView 추출
    public void deleteLastView(Long userId) {
        LocalDateTime lastTime = queryFactory
                .select(recentView.createdDate.min())
                .from(recentView)
                .where(recentView.userId.eq(userId))
                .fetchOne();

        queryFactory
                .delete(recentView)
                .where(
                        recentView.userId.eq(userId),
                        recentView.createdDate.eq(lastTime)
                ).execute();
    }

    public void deleteByBoardId(Long boardId) {
        queryFactory
                .delete(recentView)
                .where(recentView.boardId.eq(boardId))
                .execute();
    }
}
