package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.service.BoardService;
import com.aloharoombackend.service.RecentViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@Transactional
class RecentViewRepositoryTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    RecentViewRepository recentViewRepository;
    @Autowired
    RecentViewService recentViewService;
    @Autowired
    BoardService boardService;

//    @BeforeEach
//    public void before() {
//        //Home 생성
//        BoardAddDto b1 = new BoardAddDto(); b1.setFlat(30); b1.setRoomCount(3); b1.setRent(25); b1.setHomeType("apartment");
//        Home h1 = new Home(b1);
//        BoardAddDto b2 = new BoardAddDto(); b2.setFlat(32); b2.setRoomCount(1); b2.setRent(25); b2.setHomeType("apartment");
//        Home h2 = new Home(b2);
//        BoardAddDto b3 = new BoardAddDto(); b3.setFlat(35); b3.setRoomCount(2); b3.setRent(25); b3.setHomeType("apartment");
//        Home h3 = new Home(b3);
//        BoardAddDto b4 = new BoardAddDto(); b4.setFlat(40); b4.setRoomCount(4); b4.setRent(25); b4.setHomeType("apartment");
//        Home h4 = new Home(b4);
//        BoardAddDto b5 = new BoardAddDto(); b5.setFlat(40); b5.setRoomCount(4); b5.setRent(25); b5.setHomeType("apartment");
//        Home h5 = new Home(b5);
//        BoardAddDto b6 = new BoardAddDto(); b6.setFlat(40); b6.setRoomCount(4); b6.setRent(25); b6.setHomeType("apartment");
//        Home h6 = new Home(b6);
//        BoardAddDto b7 = new BoardAddDto(); b7.setFlat(40); b7.setRoomCount(4); b7.setRent(25); b7.setHomeType("apartment");
//        Home h7 = new Home(b7);
//        BoardAddDto b8 = new BoardAddDto(); b8.setFlat(40); b8.setRoomCount(4); b8.setRent(25); b8.setHomeType("apartment");
//        Home h8 = new Home(b8);
//        BoardAddDto b9 = new BoardAddDto(); b9.setFlat(40); b9.setRoomCount(4); b9.setRent(25); b9.setHomeType("apartment");
//        Home h9 = new Home(b9);
//        BoardAddDto b10 = new BoardAddDto(); b10.setFlat(40); b10.setRoomCount(4); b10.setRent(25); b10.setHomeType("apartment");
//        Home h10 = new Home(b10);
//        BoardAddDto b11 = new BoardAddDto(); b11.setFlat(40); b11.setRoomCount(4); b11.setRent(25); b11.setHomeType("apartment");
//        Home h11 = new Home(b11);
//        BoardAddDto b12 = new BoardAddDto(); b12.setFlat(40); b12.setRoomCount(4); b12.setRent(25); b12.setHomeType("apartment");
//        Home h12 = new Home(b12);
//
//        em.persist(h1);
//        em.persist(h2);
//        em.persist(h3);
//        em.persist(h4);
//        em.persist(h5);
//        em.persist(h6);
//        em.persist(h7);
//        em.persist(h8);
//        em.persist(h9);
//        em.persist(h10);
//        em.persist(h11);
//        em.persist(h12);
//
//        //User 생성
//        User u1 = new User(new SignUpDto("admin1", "1234", 20, "male"));
//        User u2 = new User(new SignUpDto("admin2", "1234", 21, "female"));
//        User u3 = new User(new SignUpDto("admin3", "1234", 22, "male"));
//        User u4 = new User(new SignUpDto("admin4", "1234", 23, "male"));
//        User u5 = new User(new SignUpDto("admin5", "1234", 20, "male"));
//        User u6 = new User(new SignUpDto("admin6", "1234", 21, "female"));
//        User u7 = new User(new SignUpDto("admin7", "1234", 22, "male"));
//        User u8 = new User(new SignUpDto("admin8", "1234", 23, "male"));
//        User u9 = new User(new SignUpDto("admin9", "1234", 20, "male"));
//        User u10 = new User(new SignUpDto("admin10", "1234", 21, "female"));
//        User u11 = new User(new SignUpDto("admin11", "1234", 22, "male"));
//        User u12 = new User(new SignUpDto("admin12", "1234", 23, "male"));
//        em.persist(u1);
//        em.persist(u2);
//        em.persist(u3);
//        em.persist(u4);
//        em.persist(u5);
//        em.persist(u6);
//        em.persist(u7);
//        em.persist(u8);
//        em.persist(u9);
//        em.persist(u10);
//        em.persist(u11);
//        em.persist(u12);
//
//        //Board 생성
//        Board board1 = new Board(h1, u1, new BoardAddDto());
//        Board board2 = new Board(h2, u2, new BoardAddDto());
//        Board board3 = new Board(h3, u3, new BoardAddDto());
//        Board board4 = new Board(h4, u4, new BoardAddDto());
//        Board board5 = new Board(h5, u5, new BoardAddDto());
//        Board board6 = new Board(h6, u6, new BoardAddDto());
//        Board board7 = new Board(h7, u7, new BoardAddDto());
//        Board board8 = new Board(h8, u8, new BoardAddDto());
//        Board board9 = new Board(h9, u9, new BoardAddDto());
//        Board board10 = new Board(h10, u10, new BoardAddDto());
//        Board board11 = new Board(h11, u11, new BoardAddDto());
//        Board board12 = new Board(h12, u12, new BoardAddDto());
//
//        em.persist(board1); em.persist(board2);
//        em.persist(board3); em.persist(board4);
//        em.persist(board5); em.persist(board6);
//        em.persist(board7); em.persist(board8);
//        em.persist(board9); em.persist(board10);
//        em.persist(board11); em.persist(board12);
//
//        RecentView recentView1 = new RecentView(1L,1L);
//        em.persist(recentView1); em.flush();
//        int j=0;
//        for (int i = 0; i < 10000; i++) {
//             j+=i;
//        }
//        RecentView recentView2 = new RecentView(2L,1L);
//        em.persist(recentView2); em.flush();
//        for (int i = 0; i < 10000; i++) {
//            j+=i;
//        }
//        RecentView recentView3 = new RecentView(3L,1L);
//        RecentView recentView4 = new RecentView(4L,1L);
//        RecentView recentView5 = new RecentView(5L,1L);
//        RecentView recentView6 = new RecentView(6L,1L);
//        RecentView recentView7 = new RecentView(7L,1L);
//        RecentView recentView8 = new RecentView(8L,1L);
//        RecentView recentView9 = new RecentView(9L,1L);
//        RecentView recentView10 = new RecentView(10L,1L);
//        em.persist(recentView2);
//        em.persist(recentView3); em.persist(recentView4);
//        em.persist(recentView5); em.persist(recentView6);
//        em.persist(recentView7); em.persist(recentView8);
//        em.persist(recentView9); em.persist(recentView10);
//
//        em.flush(); em.clear();
//    }
//
//    //제일 마지막에 본 글을 최근 본 글에서 제거
//    @Test
//    @Rollback(false)
//    void deleteLastView() {
////        boardId => 1,2 삭제 / 20,21 생성 => 성공
//        RecentView rv1 = new RecentView(20L,1L);
//        RecentView rv2 = new RecentView(21L,1L);
//
//        recentViewService.create(rv1);
//        recentViewService.create(rv2);
//    }
//
//    //최근 본 글 조회 (HeartBoardDto에서 imgUrl 받는 부분 주석처리해야 돌아감. => 이미지 데이터 안 넣었기 때문.)
//    @Test
//    @Rollback(false)
//    void recentViewBoard() {
//        List<RecentView> recentViews = recentViewService.findByUserId(1L);
//        List<Long> boardIds = recentViews.stream().map(recentView -> recentView.getBoardId()).collect(Collectors.toList());
//        for (Long boardId : boardIds) {
//            System.out.println(boardId);
//        }
//        List<HeartBoardDto> recentViewBoards = boardService.findByboardIds(boardIds);
//        for (HeartBoardDto recentViewBoard : recentViewBoards) {
//            System.out.println("recentViewBoard = " + recentViewBoard);
//        }
//    }
//
//    @Test
//    @Rollback(value = false)
//    void a() {
//        Board board = new Board();
//        board.setTitle("sda");
//        em.persist(board);
//        em.flush(); em.clear();
//        Board one = boardService.findOne(13L);
//        System.out.println(one.getTitle());
//        System.out.println(one.getHome());
//    }
}