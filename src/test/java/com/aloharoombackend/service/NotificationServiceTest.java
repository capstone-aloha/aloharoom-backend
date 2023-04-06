package com.aloharoombackend.service;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.NotificationDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import com.aloharoombackend.model.Notification;
import com.aloharoombackend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class NotificationServiceTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    NotificationService notificationService;

    @BeforeEach
    public void before() {
        //Home 생성
        BoardAddDto b1 = new BoardAddDto();
        b1.setFlat(30);
        b1.setRoomCount(3);
        b1.setRent(25);
        b1.setHomeType("apartment");
        Home h1 = new Home(b1);
        BoardAddDto b2 = new BoardAddDto();
        b1.setFlat(30);
        b1.setRoomCount(3);
        b1.setRent(25);
        b1.setHomeType("apartment");
        Home h2 = new Home(b2);
        em.persist(h1);
        em.persist(h2);

        //User 생성
        User u1 = new User(new SignUpDto("admin1", "1234", 20, "male"));
        User u2 = new User(new SignUpDto("admin2", "1234", 24, "male"));
        em.persist(u1);
        em.persist(u2);

        //Board 생성
        Board board1 = new Board(h1, u1, new BoardAddDto());
        Board board2 = new Board(h2, u2, new BoardAddDto());
        em.persist(board1);
        em.persist(board2);


        Notification n1 = new Notification(u1, board1, "테스트1");
        Notification n2 = new Notification(u1, board1, "테스트2");
        Notification n3 = new Notification(u1, board1, "테스트3");
        em.persist(n1); em.persist(n2); em.persist(n3);
        em.flush();
        n2.setCreatedDate(LocalDateTime.of(2023,4,6,16,1,30));
        n3.setCreatedDate(LocalDateTime.of(2023,4,4,15,15,30));
        em.flush(); em.clear();
    }

    /*
        알림 조회 시 24시간 내 알림이면 x분 전 or x시간 전 or 방금 전으로 표시
        알림 조회 시 24시간 외 알림이면 2023.04.06으로 표시
     */
    @Test
    @Rollback(value = false)
    void test() {
        List<NotificationDto> notification = notificationService.getNotification(1L);
        for (NotificationDto notificationDto : notification) {
            System.out.println(notificationDto);
        }
    }
}