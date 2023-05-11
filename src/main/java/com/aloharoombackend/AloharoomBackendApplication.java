package com.aloharoombackend;

import com.aloharoombackend.dto.*;
import com.aloharoombackend.model.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		BoardAddDto b1 = new BoardAddDto("방 게시물1 내용입니다.", 3, "서울특별시 성북구 삼선교로16길 116", "villa", "매매", 2000,
				10, 25, 30, 15, 8, 20, LocalDate.now(), 37.582812, 127.010233, Arrays.asList(new Integer[]{20, 25}));
		Home h1 = new Home(b1);
		BoardAddDto b2 = new BoardAddDto("방 게시물2 내용입니다.", 3, "서울특별시 영등포구 당산동5가 42", "officetel", "매매", 2000,
				10, 30, 38, 15, 2, 25, LocalDate.now(), 37.531854, 126.902958, Arrays.asList(new Integer[]{22, 25}));
		Home h2 = new Home(b2);
		BoardAddDto b3 = new BoardAddDto("방 게시물3 내용입니다.", 3, "서울특별시 강동구 강동구 고덕로390", "apartment", "매매", 2000,
				10, 29, 20, 15, 2, 25, LocalDate.now(), 37.555031, 127.168604, Arrays.asList(new Integer[]{26, 28}));
		Home h3 = new Home(b3);
		BoardAddDto b4 = new BoardAddDto("방 게시물4 내용입니다.", 3, "서울특별시 강서구 공항대로 247", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.559246, 126.834986, Arrays.asList(new Integer[]{29, 29}));
		Home h4 = new Home(b4);
		BoardAddDto b5 = new BoardAddDto("방 게시물5 내용입니다.", 3, "서울특별시 강남구 언주로103길 43", "apartment", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.505775, 127.036566, Arrays.asList(new Integer[]{29, 29}));
		Home h5 = new Home(b5);
		BoardAddDto b6 = new BoardAddDto("방 게시물6 내용입니다.", 3, "서울특별시 동작구 흑석동 204-38", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.507155, 126.957409, Arrays.asList(new Integer[]{29, 29}));
		Home h6 = new Home(b6);
		BoardAddDto b7 = new BoardAddDto("방 게시물7 내용입니다.", 3, "서울특별시 용산구 이촌로87길 13", "officetel", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.519233, 126.977466, Arrays.asList(new Integer[]{29, 29}));
		Home h7 = new Home(b7);
		BoardAddDto b8 = new BoardAddDto("방 게시물8 내용입니다.", 3, "서울특별시 용산구 동빙고동 251-9", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.523249, 126.994172, Arrays.asList(new Integer[]{29, 29}));
		Home h8 = new Home(b8);
		BoardAddDto b9 = new BoardAddDto("방 게시물9 내용입니다.", 3, "서울특별시 서초구 방배동 782-33", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.492939, 126.983640, Arrays.asList(new Integer[]{29, 29}));
		Home h9 = new Home(b9);
		BoardAddDto b10 = new BoardAddDto("방 게시물10 내용입니다.", 3, "서울특별시 마포구 만리재로 41", "officetel", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.545287, 126.955292, Arrays.asList(new Integer[]{29, 29}));
		Home h10 = new Home(b10);

		BoardAddDto b11 = new BoardAddDto("방 게시물11 내용입니다.", 3, "서울특별시 중구 신당동 290-46", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.562099, 127.016063, Arrays.asList(new Integer[]{29, 29}));
		Home h11 = new Home(b11);

		BoardAddDto b12 = new BoardAddDto("방 게시물12 내용입니다.", 3, "서울특별시 서대문구 창천동 465", "apartment", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.560398, 126.927488, Arrays.asList(new Integer[]{29, 29}));
		Home h12 = new Home(b12);

		BoardAddDto b13 = new BoardAddDto("방 게시물13 내용입니다.", 3, "서울특별시 용산구 용산동2가 37-8", "officetel", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.542970, 126.987038, Arrays.asList(new Integer[]{29, 29}));
		Home h13 = new Home(b13);

		BoardAddDto b14 = new BoardAddDto("방 게시물14 내용입니다.", 3, "서울특별시 서초구 반포동 551-6", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.498003, 127.000882, Arrays.asList(new Integer[]{29, 29}));
		Home h14 = new Home(b14);

		BoardAddDto b15 = new BoardAddDto("방 게시물15 내용입니다.", 3, "서울특별시 용산구 서빙고로65길 18", "officetel", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.520979, 126.994425, Arrays.asList(new Integer[]{29, 29}));
		Home h15 = new Home(b15);

		BoardAddDto b16 = new BoardAddDto("방 게시물16 내용입니다.", 3, "서울특별시 영등포구 버드나루로11길 5", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.524566, 126.908684, Arrays.asList(new Integer[]{29, 29}));
		Home h16 = new Home(b16);

		BoardAddDto b17 = new BoardAddDto("방 게시물17 내용입니다.", 3, "서울특별시 영등포구 양평동4가 317-3", "apartment", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.538441, 126.897458, Arrays.asList(new Integer[]{29, 29}));
		Home h17 = new Home(b17);

		BoardAddDto b18 = new BoardAddDto("방 게시물18 내용입니다.", 3, "서울특별시 동작구 노량진동 205-55", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.511967, 126.947980, Arrays.asList(new Integer[]{29, 29}));
		Home h18 = new Home(b18);

		BoardAddDto b19 = new BoardAddDto("방 게시물19 내용입니다.", 3, "서울특별시 용산구 원효로81길 20-30", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.539469, 126.966294, Arrays.asList(new Integer[]{29, 29}));
		Home h19 = new Home(b19);

		BoardAddDto b20 = new BoardAddDto("방 게시물20 내용입니다.", 3, "서울특별시 서초구 서초대로54길 48-20", "officetel", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.492062, 127.015781, Arrays.asList(new Integer[]{29, 29}));
		Home h20 = new Home(b20);

		BoardAddDto b21 = new BoardAddDto("방 게시물21 내용입니다.", 3, "서울특별시 강남구 역삼동 725-11", "villa", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.499602, 127.039329, Arrays.asList(new Integer[]{29, 29}));
		Home h21 = new Home(b21);

		em.persist(h1); em.persist(h2);
		em.persist(h3); em.persist(h4);
		em.persist(h5); em.persist(h6);
		em.persist(h7); em.persist(h8);
		em.persist(h9); em.persist(h10);
		em.persist(h11); em.persist(h12);
		em.persist(h13); em.persist(h14);
		em.persist(h15); em.persist(h16);
		em.persist(h17); em.persist(h18);
		em.persist(h19); em.persist(h20);
		em.persist(h21);


		//방 사진 등록
		List<String> imgUrls = new ArrayList<>(
				List.of(new String[]{"efdded1a-a0c8-4018-bfd6-a9389087bbb4.jpg","dca186b1-c4b2-452d-a4ec-22f039951754.jpg",
						"78207f64-5aa1-458d-a6ad-43106806a632.jpg", "2dc5fbd5-c916-405c-bba3-1b22f2c2c39a.jpg"}));
		imgUrls.stream().map(imgUrl -> new HomeImage(h1, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h2, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h3, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h4, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h5, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h6, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h7, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h8, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h9, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h10, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h11, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h12, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h13, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h14, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h15, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h16, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h17, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h18, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h19, imgUrl)).collect(Collectors.toList()); imgUrls.stream().map(imgUrl -> new HomeImage(h20, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h21, imgUrl)).collect(Collectors.toList());

		//User 생성
		String encodePw = bCryptPasswordEncoder.encode("1234");
		SignUpDto su1 = new SignUpDto("admin1@naver.com", encodePw, 20, "male");
		su1.setNickname("admin1"); User u1 = new User(su1); u1.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su2 = new SignUpDto("admin2@naver.com", encodePw, 21, "female");
		su2.setNickname("admin2"); User u2 = new User(su2); u2.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su3 = new SignUpDto("admin3@naver.com", encodePw, 22, "male");
		su3.setNickname("admin3"); User u3 = new User(su3); u3.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su4 = new SignUpDto("admin4@naver.com", encodePw, 23, "male");
		su4.setNickname("admin4"); User u4 = new User(su4); u4.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su5 = new SignUpDto("admin5@naver.com", encodePw, 23, "male");
		su5.setNickname("admin5"); User u5 = new User(su5); u5.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su6 = new SignUpDto("admin6@naver.com", encodePw, 23, "male");
		su6.setNickname("admin6"); User u6 = new User(su6); u6.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su7 = new SignUpDto("admin7@naver.com", encodePw, 23, "male");
		su7.setNickname("admin7"); User u7 = new User(su7); u7.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su8 = new SignUpDto("admin8@naver.com", encodePw, 23, "male");
		su8.setNickname("admin8"); User u8 = new User(su8); u8.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su9 = new SignUpDto("admin9@naver.com", encodePw, 23, "male");
		su9.setNickname("admin9"); User u9 = new User(su9); u9.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su10 = new SignUpDto("admin10@naver.com", encodePw, 23, "male");
		su10.setNickname("admin10"); User u10 = new User(su10); u10.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su11 = new SignUpDto("admin11@naver.com", encodePw, 23, "male");
		su11.setNickname("admin11"); User u11 = new User(su11); u11.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su12 = new SignUpDto("admin12@naver.com", encodePw, 23, "male");
		su12.setNickname("admin12"); User u12 = new User(su12); u12.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su13 = new SignUpDto("admin13@naver.com", encodePw, 23, "male");
		su13.setNickname("admin13"); User u13 = new User(su13); u13.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su14 = new SignUpDto("admin14@naver.com", encodePw, 23, "male");
		su14.setNickname("admin14"); User u14 = new User(su14); u14.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su15 = new SignUpDto("admin15@naver.com", encodePw, 23, "male");
		su15.setNickname("admin15"); User u15 = new User(su15); u15.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su16 = new SignUpDto("admin16@naver.com", encodePw, 23, "male");
		su16.setNickname("admin16"); User u16 = new User(su16); u16.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su17 = new SignUpDto("admin17@naver.com", encodePw, 23, "male");
		su17.setNickname("admin17"); User u17 = new User(su17); u17.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su18 = new SignUpDto("admin18@naver.com", encodePw, 23, "male");
		su18.setNickname("admin18"); User u18 = new User(su18); u18.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su19 = new SignUpDto("admin19@naver.com", encodePw, 23, "male");
		su19.setNickname("admin19"); User u19 = new User(su19); u19.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su20 = new SignUpDto("admin20@naver.com", encodePw, 23, "male");
		su20.setNickname("admin20"); User u20 = new User(su20); u20.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su21 = new SignUpDto("admin21@naver.com", encodePw, 23, "male");
		su21.setNickname("admin21"); User u21 = new User(su21); u21.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		em.persist(u1); em.persist(u2);
		em.persist(u3); em.persist(u4);
		em.persist(u5); em.persist(u6);
		em.persist(u7); em.persist(u8);
		em.persist(u9); em.persist(u10);
		em.persist(u11); em.persist(u12);
		em.persist(u13); em.persist(u14);
		em.persist(u15); em.persist(u16);
		em.persist(u17); em.persist(u18);
		em.persist(u19); em.persist(u20);
		em.persist(u21);

		//Board 생성
		Board board1 = new Board(h1, u1, b1); Board board2 = new Board(h2, u2, b2);
		Board board3 = new Board(h3, u3, b3); Board board4 = new Board(h4, u4, b4);
		Board board5 = new Board(h5, u5, b5); Board board6 = new Board(h6, u6, b6);
		Board board7 = new Board(h7, u7, b7); Board board8 = new Board(h8, u8, b8);
		Board board9 = new Board(h9, u9, b9); Board board10 = new Board(h10, u21, b10);
		Board board11 = new Board(h11, u11, b11); Board board12 = new Board(h12, u12, b12);
		Board board13 = new Board(h13, u13, b13); Board board14 = new Board(h14, u14, b14);
		Board board15 = new Board(h15, u15, b15); Board board16 = new Board(h16, u16, b16);
		Board board17 = new Board(h17, u17, b17); Board board18 = new Board(h18, u18, b18);
		Board board19 = new Board(h19, u19, b19); Board board20 = new Board(h20, u20, b20);
		Board board21 = new Board(h21, u21, b21);
		em.persist(board1); em.persist(board2);
		em.persist(board3); em.persist(board4);
		em.persist(board5); em.persist(board6);
		em.persist(board7); em.persist(board8);
		em.persist(board9); em.persist(board10);
		em.persist(board11); em.persist(board12);
		em.persist(board13); em.persist(board14);
		em.persist(board15); em.persist(board16);
		em.persist(board17); em.persist(board18);
		em.persist(board19); em.persist(board20);
		em.persist(board21);

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

		MyHashtag myHashtag10 = new MyHashtag(u21, "조용한");
		MyHashtag myHashtag11 = new MyHashtag(u21, "아침형");
		MyHashtag myHashtag12 = new MyHashtag(u21, "배달의 민족");

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

		//Community 생성
		CommunityBoardDto c1 = new CommunityBoardDto("제목1(방자랑)", "방자랑1", 1);
		CommunityBoardDto c2 = new CommunityBoardDto("제목1(정보공유)", "정보공유1", 2);
		CommunityBoardDto c3 = new CommunityBoardDto("제목2(방자랑)", "방자랑2", 1);
		CommunityBoardDto c4 = new CommunityBoardDto("제목1(자유)", "자유1", 3);
		CommunityBoardDto c5 = new CommunityBoardDto("제목2(정보공유)", "정보공유2", 2);
		CommunityBoardDto c6 = new CommunityBoardDto("제목2(자유)", "자유2", 3);
		CommunityBoardDto c7 = new CommunityBoardDto("제목3(방자랑)", "방자랑3", 1);
		CommunityBoardDto c8 = new CommunityBoardDto("제목3(정보공유)", "정보공유3", 2);

		CommunityBoard communityBoard1 = new CommunityBoard(u1, c1);
		CommunityBoard communityBoard2 = new CommunityBoard(u1, c2);
		CommunityBoard communityBoard3 = new CommunityBoard(u2, c3);
		CommunityBoard communityBoard4 = new CommunityBoard(u2, c4);
		CommunityBoard communityBoard5 = new CommunityBoard(u3, c5);
		CommunityBoard communityBoard6 = new CommunityBoard(u3, c6);
		CommunityBoard communityBoard7 = new CommunityBoard(u21, c7);
		CommunityBoard communityBoard8 = new CommunityBoard(u21, c8);

		//커뮤니티 이미지(방 이미지와 동일하게 해둠)
		List<String> CommunityImgUrls = new ArrayList<>(
				List.of(new String[]{"efdded1a-a0c8-4018-bfd6-a9389087bbb4.jpg","dca186b1-c4b2-452d-a4ec-22f039951754.jpg",
						"78207f64-5aa1-458d-a6ad-43106806a632.jpg", "2dc5fbd5-c916-405c-bba3-1b22f2c2c39a.jpg"}));
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard1, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard2, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard3, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard4, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard5, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard6, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard7, CommunityImgUrlsimgUrl)).collect(Collectors.toList());
		CommunityImgUrls.stream().map(CommunityImgUrlsimgUrl -> new CommunityImage(communityBoard8, CommunityImgUrlsimgUrl)).collect(Collectors.toList());

		em.persist(communityBoard1);
		em.persist(communityBoard2);
		em.persist(communityBoard3);
		em.persist(communityBoard4);
		em.persist(communityBoard5);
		em.persist(communityBoard6);
		em.persist(communityBoard7);
		em.persist(communityBoard8);

		Comment cm1 = new Comment(u1, board1, new AddCommentDto(1L, 1L, 1L, 0, "댓글1", null, 0, null));
		Comment cm2 = new Comment(u2, board1, new AddCommentDto(2L, 2L, 1L, 0, "댓글2", null, 0, null));
		Comment cm3 = new Comment(u3, board1, new AddCommentDto(3L, 1L, 1L, 0, "댓글1-1", "댓글1", 1, 1L));
		Comment cm4 = new Comment(u3, board1, new AddCommentDto(3L, 1L, 1L, 0, "댓글1-2", "댓글1", 1, 1L));

		em.persist(cm1);
		em.persist(cm2);
		em.persist(cm3);
		em.persist(cm4);

		Notification n1 = new Notification(u1, u2.getProfileUrl(), board1, "내 게시글 \"서울특별시 성북...\" 글에 admin2님이 댓글을 남겼습니다.");
		Notification n2 = new Notification(u1, u3.getProfileUrl(), board1, "내 댓글 \"댓글1\"에 admin3님이 댓글을 남겼습니다.");
		Notification n3 = new Notification(u1, u3.getProfileUrl(), board1, "내 댓글 \"댓글1\"에 admin3님이 댓글을 남겼습니다.");
		Notification n4 = new Notification(u1, u3.getProfileUrl(), board1, "내 댓글 \"댓글1\"에 admin3님이 댓글을 남겼습니다.");
		Notification n5 = new Notification(u1, u3.getProfileUrl(), board1, "내 댓글 \"댓글1\"에 admin3님이 댓글을 남겼습니다.");
		em.persist(n1); em.persist(n2);
		em.persist(n3); em.persist(n4);
		em.persist(n5);

		em.flush(); em.clear();
	}
}
