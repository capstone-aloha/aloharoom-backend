package com.aloharoombackend;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.Home;
import com.aloharoombackend.model.MyHashtag;
import com.aloharoombackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class AloharoomBackendApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(AloharoomBackendApplication.class, args);
	}


	@PersistenceContext
	EntityManager em;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//Home 생성
		BoardAddDto b1 = new BoardAddDto("방 게시물1 내용입니다.",3,"서울특별시 성북구 삼선교로16길 116","apartment","매매",2000,
				10,25,30,15,8,20,LocalDate.now(),200.,200.);
		Home h1 = new Home(b1);
		BoardAddDto b2 = new BoardAddDto("방 게시물2 내용입니다.",3,"서울특별시 영등포구 당산동5가 42","apartment","매매",2000,
				10,30,38,15,2,25,LocalDate.now(),200.,200.);
		Home h2 = new Home(b2);
		BoardAddDto b3 = new BoardAddDto("방 게시물3 내용입니다.",3,"서울특별시 강동구 강동구 고덕로390","apartment","매매",2000,
				10,29,20,15,2,25,LocalDate.now(),200.,200.);
		Home h3 = new Home(b3);
		BoardAddDto b4 = new BoardAddDto("방 게시물4 내용입니다.",3,"서울특별시 강서구 공항대로 247","apartment","매매",2000,
				10,40,34,15,2,25,LocalDate.now(),200.,200.);
		Home h4 = new Home(b4);
		em.persist(h1);
		em.persist(h2);
		em.persist(h3);
		em.persist(h4);

		//User 생성
		String encodePw = bCryptPasswordEncoder.encode("1234");
		SignUpDto su1 = new SignUpDto("admin1@naver.com", encodePw, 20, "male");
		su1.setNickname("admin1"); User u1 = new User(su1);
		SignUpDto su2 = new SignUpDto("admin2@naver.com", encodePw, 21, "female");
		su2.setNickname("admin2"); User u2 = new User(su2);
		SignUpDto su3 = new SignUpDto("admin3@naver.com", encodePw, 22, "male");
		su3.setNickname("admin3"); User u3 = new User(su3);
		SignUpDto su4 = new SignUpDto("admin4@naver.com", encodePw, 23, "male");
		su4.setNickname("admin4"); User u4 = new User(su4);
		em.persist(u1);
		em.persist(u2);
		em.persist(u3);
		em.persist(u4);

		//Board 생성
		Board board1 = new Board(h1, u1, b1);
		Board board2 = new Board(h2, u2, b2);
		Board board3 = new Board(h3, u3, b3);
		Board board4 = new Board(h4, u4, b4);
		em.persist(board1); em.persist(board2);
		em.persist(board3); em.persist(board4);

		//MyHashtag 생성
		MyHashtag myHashtag1 = new MyHashtag(u1, "조용한");
		MyHashtag myHashtag2 = new MyHashtag(u1, "아침형");
		MyHashtag myHashtag3 = new MyHashtag(u1, "배달의 민족");

		MyHashtag myHashtag4 = new MyHashtag(u2, "조용한");
		MyHashtag myHashtag5 = new MyHashtag(u2, "아침형");
		MyHashtag myHashtag6 = new MyHashtag(u2, "배달의 민족");

		MyHashtag myHashtag7 = new MyHashtag(u3, "조용한");
		MyHashtag myHashtag8 = new MyHashtag(u3, "야간형");
		MyHashtag myHashtag9 = new MyHashtag(u3, "배달의 민족");

		MyHashtag myHashtag10 = new MyHashtag(u4, "조용한");
		MyHashtag myHashtag11 = new MyHashtag(u4, "아침형");
		MyHashtag myHashtag12 = new MyHashtag(u4, "배달의 민족");

		em.persist(myHashtag1);
		em.persist(myHashtag2);
		em.persist(myHashtag3);
		em.persist(myHashtag4);
		em.persist(myHashtag5);
		em.persist(myHashtag6);
		em.persist(myHashtag7);
		em.persist(myHashtag8);
		em.persist(myHashtag9);
		em.persist(myHashtag10);
		em.persist(myHashtag11);
		em.persist(myHashtag12);

		em.flush(); em.clear();
	}
}
