package com.aloharoombackend.service;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.CommentDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@SpringBootTest
@Transactional
class HomeCommentServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    UserService userService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentService homeCommentService;

//    @BeforeEach
//    public void before() {
//        //Home 생성
//        BoardAddDto b1 = new BoardAddDto(); b1.setFlat(30); b1.setRoomCount(3); b1.setRent(25); b1.setHomeType("apartment");
//        Home h1 = new Home(b1);
//        BoardAddDto b2 = new BoardAddDto(); b1.setFlat(30); b1.setRoomCount(3); b1.setRent(25); b1.setHomeType("apartment");
//        Home h2 = new Home(b2);
//        em.persist(h1); em.persist(h2);
//
//        //User 생성
//        User u1 = new User(new SignUpDto("admin1", "1234", 20, "male"));
//        User u2 = new User(new SignUpDto("admin2", "1234", 24, "male"));
//        em.persist(u1); em.persist(u2);
//
//        //Board 생성
//        Board board1 = new Board(h1, u1, new BoardAddDto());
//        Board board2 = new Board(h2, u2, new BoardAddDto());
//        em.persist(board1); em.persist(board2);
//
//        //groupNum은 어떻게 넣지? => 새로 입력할 경우 => groupNum이 null이면 id로 대체
//        //댓글 작성(게시물1)
//        Comment hc1 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L,"댓글1", 0, 1L));
//        Comment hc2 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, "댓글2", 0, 2L));
//        Comment hc3 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, "댓글3", 0, 3L));
//        Comment hc4 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, "댓글2-1", 1, 2L));
//        Comment hc5 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, "댓글1-1", 1, 1L));
//        Comment hc6 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, "댓글2-2", 1, 2L));
//        Comment hc7 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, "댓글2-3", 1, 2L));
//        //댓글 작성(게시물2)
//        Comment hc8 = new Comment(u2, board2, new AddCommentDto(2L, 2L, 2L, "댓글1", 0, 8L));
//        Comment hc9 = new Comment(u2, board2, new AddCommentDto(2L, 2L, 2L, "댓글2", 0, 9L));
//        Comment hc10 = new Comment(u2, board2, new AddCommentDto(2L, 2L, 2L, "댓글1-1", 1, 8L));
//
//        em.persist(hc1); em.persist(hc2);
//        em.persist(hc3); em.persist(hc4);
//        em.persist(hc5);
//        em.persist(hc6);
//        em.persist(hc7); em.persist(hc8);
//        em.persist(hc9); em.persist(hc10);
//        em.flush(); em.clear();
//    }

    @Test
    void getComment() {
        List<CommentDto> comment = homeCommentService.getHomeComment(1L);
        for (CommentDto commentDto : comment) {
            System.out.println(commentDto);
        }
        System.out.println("================================================");
        comment = homeCommentService.getHomeComment(2L);
        for (CommentDto commentDto : comment) {
            System.out.println(commentDto);
        }
    }

    @Test
    void deleteComment() {
        homeCommentService.deleteComment(1L);
        List<CommentDto> commentDtos = homeCommentService.getHomeComment(1L);
        for (CommentDto commentDto : commentDtos) {
            System.out.println("commentDto = " + commentDto);
        }
        Assertions.assertThat(commentDtos.get(0).getContent()).isEqualTo("삭제된 댓글입니다.");
    }

}