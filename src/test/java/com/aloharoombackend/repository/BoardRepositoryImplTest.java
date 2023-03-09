package com.aloharoombackend.repository;

import com.aloharoombackend.model.LikeHashtag;
import com.aloharoombackend.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.aloharoombackend.model.QUser.user;

@SpringBootTest
@Transactional
class BoardRepositoryImplTest {
    //테스트 해봐야 in쿼리 작동하는지 알듯

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        User u1 = new User("admin1", "1234");
        User u2 = new User("admin2", "1234");
        User u3 = new User("admin3", "1234");
        User u4 = new User("admin4", "1234");
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);

        LikeHashtag likeHashtag1 = new LikeHashtag(u1, "admin1-1");
        LikeHashtag likeHashtag2 = new LikeHashtag(u1, "admin1-2");
        LikeHashtag likeHashtag3 = new LikeHashtag(u1, "admin1-3");

        LikeHashtag likeHashtag4 = new LikeHashtag(u2, "admin2-1");
        LikeHashtag likeHashtag5 = new LikeHashtag(u2, "admin2-2");
        LikeHashtag likeHashtag6 = new LikeHashtag(u2, "admin2-3");

        LikeHashtag likeHashtag7 = new LikeHashtag(u3, "admin3-1");
        LikeHashtag likeHashtag8 = new LikeHashtag(u3, "admin3-2");
        LikeHashtag likeHashtag9 = new LikeHashtag(u3, "admin3-3");

        LikeHashtag likeHashtag10 = new LikeHashtag(u4, "admin4-1");
        LikeHashtag likeHashtag11 = new LikeHashtag(u4, "admin4-2");
        LikeHashtag likeHashtag12 = new LikeHashtag(u4, "admin4-3");

        em.persist(likeHashtag1);
        em.persist(likeHashtag2);
        em.persist(likeHashtag3);
        em.persist(likeHashtag4);
        em.persist(likeHashtag5);
        em.persist(likeHashtag6);
        em.persist(likeHashtag7);
        em.persist(likeHashtag8);
        em.persist(likeHashtag9);
        em.persist(likeHashtag10);
        em.persist(likeHashtag11);
        em.persist(likeHashtag12);
        em.flush(); em.clear();
    }

    @Test
    @Rollback(false)
    public void batchSizeTest() {
        //select Board 가 User수 만큼 나가는데 왜 나가는지 모르겠다 => User가 Board의 주인어어서 무조건 나가는 듯?
        List<User> users = queryFactory
                .selectFrom(user).distinct()
                .join(user.likeHashtags)
                .leftJoin(user.board).fetchJoin() //이래야 사용자마다 select Board 쿼리 1번씩 안 나감
                .leftJoin(user.home)
                .fetch();
        System.out.println("user수: " + users.size());

        //모든 유저의 해시태그 => 실 객체 변환 (In쿼리 1번 발생)
        users.stream().forEach(user1 -> user1.getLikeHashtags().stream().forEach(likeHashtag1 -> likeHashtag1.getHash()));
    }
}

