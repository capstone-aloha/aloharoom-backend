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
		BoardAddDto b1 = new BoardAddDto("방 게시물1 내용입니다.", "https://open.kakao.com/o/saQor8jf",3, "서울특별시 성북구 삼선교로16길 116", "villa", "매매", 22000,
				null, 25, 30, 15, 8, 20, LocalDate.now(), 37.582812, 127.010233, Arrays.asList(new Integer[]{20, 25}));
		Home h1 = new Home(b1);
		BoardAddDto b2 = new BoardAddDto("방 게시물2 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 영등포구 당산동5가 42", "officetel", "월세", 30,
				500, 30, 38, 15, 2, 25, LocalDate.now(), 37.531854, 126.902958, Arrays.asList(new Integer[]{22, 25}));
		Home h2 = new Home(b2);
		BoardAddDto b3 = new BoardAddDto("방 게시물3 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 강동구 강동구 고덕로390", "apartment", "월세", 35,
				700, 29, 20, 15, 2, 25, LocalDate.now(), 37.555031, 127.168604, Arrays.asList(new Integer[]{26, 28}));
		Home h3 = new Home(b3);
		BoardAddDto b4 = new BoardAddDto("방 게시물4 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 강서구 공항대로 247", "villa", "월세", 25,
				300, 40, 34, 15, 2, 25, LocalDate.now(), 37.559246, 126.834986, Arrays.asList(new Integer[]{29, 29}));
		Home h4 = new Home(b4);
		BoardAddDto b5 = new BoardAddDto("방 게시물5 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 강남구 언주로103길 43", "apartment", "월세", 80,
				1000, 40, 34, 15, 2, 25, LocalDate.now(), 37.505775, 127.036566, Arrays.asList(new Integer[]{29, 29}));
		Home h5 = new Home(b5);
		BoardAddDto b6 = new BoardAddDto("방 게시물6 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 동작구 흑석동 204-38", "villa", "월세", 80,
				300, 40, 34, 15, 2, 25, LocalDate.now(), 37.507155, 126.957409, Arrays.asList(new Integer[]{29, 29}));
		Home h6 = new Home(b6);
		BoardAddDto b7 = new BoardAddDto("방 게시물7 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 용산구 이촌로87길 13", "officetel", "전세", 15000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.519233, 126.977466, Arrays.asList(new Integer[]{29, 29}));
		Home h7 = new Home(b7);
		BoardAddDto b8 = new BoardAddDto("방 게시물8 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 용산구 동빙고동 251-9", "villa", "전세", 23000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.523249, 126.994172, Arrays.asList(new Integer[]{29, 29}));
		Home h8 = new Home(b8);
		BoardAddDto b9 = new BoardAddDto("방 게시물9 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 서초구 방배동 782-33", "villa", "전세", 35000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.492939, 126.983640, Arrays.asList(new Integer[]{29, 29}));
		Home h9 = new Home(b9);
		BoardAddDto b10 = new BoardAddDto("방 게시물10 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 마포구 만리재로 41", "officetel", "전세", 12000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.545287, 126.955292, Arrays.asList(new Integer[]{29, 29}));
		Home h10 = new Home(b10);

		BoardAddDto b11 = new BoardAddDto("방 게시물11 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 중구 신당동 290-46", "villa", "전세", 8000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.562099, 127.016063, Arrays.asList(new Integer[]{29, 29}));
		Home h11 = new Home(b11);

		BoardAddDto b12 = new BoardAddDto("방 게시물12 내용입니다.", "https://open.kakao.com/o/saQor8jf", 1, "서울특별시 서대문구 창천동 465", "apartment", "전세", 10000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.560398, 126.927488, Arrays.asList(new Integer[]{29, 29}));
		Home h12 = new Home(b12);

		BoardAddDto b13 = new BoardAddDto("방 게시물13 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 용산구 용산동2가 37-8", "officetel", "전세", 30000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.542970, 126.987038, Arrays.asList(new Integer[]{29, 29}));
		Home h13 = new Home(b13);

		BoardAddDto b14 = new BoardAddDto("방 게시물14 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 서초구 반포동 551-6", "villa", "전세", 2000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.498003, 127.000882, Arrays.asList(new Integer[]{29, 29}));
		Home h14 = new Home(b14);

		BoardAddDto b15 = new BoardAddDto("방 게시물15 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 용산구 서빙고로65길 18", "officetel", "월세", 70,
				800, 40, 34, 15, 2, 25, LocalDate.now(), 37.520979, 126.994425, Arrays.asList(new Integer[]{29, 29}));
		Home h15 = new Home(b15);

		BoardAddDto b16 = new BoardAddDto("방 게시물16 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 영등포구 버드나루로11길 5", "villa", "월세", 45,
				400, 40, 34, 15, 2, 25, LocalDate.now(), 37.524566, 126.908684, Arrays.asList(new Integer[]{29, 29}));
		Home h16 = new Home(b16);

		BoardAddDto b17 = new BoardAddDto("방 게시물17 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 영등포구 양평동4가 317-3", "apartment", "월세", 55,
				300, 40, 34, 15, 2, 25, LocalDate.now(), 37.538441, 126.897458, Arrays.asList(new Integer[]{29, 29}));
		Home h17 = new Home(b17);

		BoardAddDto b18 = new BoardAddDto("방 게시물18 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 동작구 노량진동 205-55", "villa", "월세", 40,
				500, 40, 34, 15, 2, 25, LocalDate.now(), 37.511967, 126.947980, Arrays.asList(new Integer[]{29, 29}));
		Home h18 = new Home(b18);

		BoardAddDto b19 = new BoardAddDto("방 게시물19 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 용산구 원효로81길 20-30", "villa", "월세", 70,
				600, 40, 34, 15, 2, 25, LocalDate.now(), 37.539469, 126.966294, Arrays.asList(new Integer[]{29, 29}));
		Home h19 = new Home(b19);

		BoardAddDto b20 = new BoardAddDto("방 게시물20 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 서초구 서초대로54길 48-20", "officetel", "월세", 100,
				1000, 40, 34, 15, 2, 25, LocalDate.now(), 37.492062, 127.015781, Arrays.asList(new Integer[]{29, 29}));
		Home h20 = new Home(b20);

		BoardAddDto b21 = new BoardAddDto("방 게시물21 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 강남구 역삼동 725-11", "villa", "월세", 120,
				1300, 40, 34, 15, 2, 25, LocalDate.now(), 37.499602, 127.039329, Arrays.asList(new Integer[]{29, 29}));
		Home h21 = new Home(b21);

		BoardAddDto b22 = new BoardAddDto("방 게시물22 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 영등포구 디지털로56길 5-3", "officetel", "월세", 40,
				300, 40, 34, 15, 2, 25, LocalDate.now(), 37.490784, 126.902512, Arrays.asList(new Integer[]{29, 29}));
		Home h22 = new Home(b22);

		BoardAddDto b23 = new BoardAddDto("방 게시물23 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 관악구 신림동 490-5", "officetel", "매매", 13000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.484670, 126.919534, Arrays.asList(new Integer[]{29, 29}));
		Home h23 = new Home(b23);

		BoardAddDto b24 = new BoardAddDto("방 게시물24 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 성동구 독서당로63길 29-1", "villa", "매매", 24000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.552828, 127.031446, Arrays.asList(new Integer[]{29, 29}));
		Home h24 = new Home(b24);

		BoardAddDto b25 = new BoardAddDto("방 게시물25 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 성동구 성덕정15길 7-1", "officetel", "매매", 28000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.583421, 127.053611, Arrays.asList(new Integer[]{29, 29}));
		Home h25 = new Home(b25);

		BoardAddDto b26 = new BoardAddDto("방 게시물26 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 서대문구 천연동 98-17", "officetel", "매매", 18000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.568223, 126.960107, Arrays.asList(new Integer[]{29, 29}));
		Home h26 = new Home(b26);

		BoardAddDto b27 = new BoardAddDto("방 게시물27 내용입니다.", "https://open.kakao.com/o/saQor8jf", 3, "서울특별시 강동구 암사동 485-17", "officetel", "매매", 14000,
				null, 40, 34, 15, 2, 25, LocalDate.now(), 37.549212, 127.134650, Arrays.asList(new Integer[]{29, 29}));
		Home h27 = new Home(b27);

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
		em.persist(h21); em.persist(h22);
		em.persist(h23); em.persist(h24);
		em.persist(h25); em.persist(h26);
		em.persist(h27);

		//방 사진 등록
		List<String> imgUrls = new ArrayList<>(
				List.of(new String[]{"efdded1a-a0c8-4018-bfd6-a9389087bbb4.jpg","dca186b1-c4b2-452d-a4ec-22f039951754.jpg",
						"78207f64-5aa1-458d-a6ad-43106806a632.jpg", "2dc5fbd5-c916-405c-bba3-1b22f2c2c39a.jpg"}));
		imgUrls.stream().map(imgUrl -> new HomeImage(h1, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h2, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h3, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h4, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h5, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h6, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h7, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h8, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h9, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h10, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h11, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h12, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h13, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h14, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h15, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h16, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h17, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h18, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h19, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h20, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h21, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h22, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h23, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h24, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h25, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h26, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));
		imgUrls.stream().map(imgUrl -> new HomeImage(h27, imgUrl)).collect(Collectors.toList()).stream().forEach(homeImage -> em.persist(homeImage));

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

		SignUpDto su22 = new SignUpDto("admin22@naver.com", encodePw, 23, "male");
		su22.setNickname("admin22"); User u22 = new User(su22); u22.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su23 = new SignUpDto("admin23@naver.com", encodePw, 23, "male");
		su23.setNickname("admin23"); User u23 = new User(su23); u23.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su24 = new SignUpDto("admin24@naver.com", encodePw, 23, "male");
		su24.setNickname("admin24"); User u24 = new User(su24); u24.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su25 = new SignUpDto("admin25@naver.com", encodePw, 23, "male");
		su25.setNickname("admin25"); User u25 = new User(su25); u25.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su26 = new SignUpDto("admin26@naver.com", encodePw, 23, "male");
		su26.setNickname("admin26"); User u26 = new User(su26); u26.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
		SignUpDto su27 = new SignUpDto("admin27@naver.com", encodePw, 23, "male");
		su27.setNickname("admin27"); User u27 = new User(su27); u27.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/e10274e8-8001-4cdd-8b4e-f527a916eafa.png");
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
		em.persist(u21); em.persist(u22);
		em.persist(u23); em.persist(u24);
		em.persist(u25); em.persist(u26);
		em.persist(u27);

		//Board 생성
		Board board1 = new Board(h1, u1, b1); Board board2 = new Board(h2, u2, b2);
		Board board3 = new Board(h3, u3, b3); Board board4 = new Board(h4, u4, b4);
		Board board5 = new Board(h5, u5, b5); Board board6 = new Board(h6, u6, b6);
		Board board7 = new Board(h7, u7, b7); Board board8 = new Board(h8, u8, b8);
		Board board9 = new Board(h9, u9, b9); Board board10 = new Board(h10, u10, b10);
		Board board11 = new Board(h11, u11, b11); Board board12 = new Board(h12, u12, b12);
		Board board13 = new Board(h13, u13, b13); Board board14 = new Board(h14, u14, b14);
		Board board15 = new Board(h15, u15, b15); Board board16 = new Board(h16, u16, b16);
		Board board17 = new Board(h17, u17, b17); Board board18 = new Board(h18, u18, b18);
		Board board19 = new Board(h19, u19, b19); Board board20 = new Board(h20, u20, b20);
		Board board21 = new Board(h21, u21, b21); Board board22 = new Board(h22, u22, b22);
		Board board23 = new Board(h23, u23, b23); Board board24 = new Board(h24, u24, b24);
		Board board25 = new Board(h25, u25, b25); Board board26 = new Board(h26, u26, b26);
		Board board27 = new Board(h27, u27, b27);
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
		em.persist(board21); em.persist(board22);
		em.persist(board23); em.persist(board24);
		em.persist(board25); em.persist(board26);
		em.persist(board27);

		//MyHashtag 생성
		MyHashtag myHashtag1 = new MyHashtag(u1, "조용한");
		MyHashtag myHashtag2 = new MyHashtag(u1, "아침형");
		MyHashtag myHashtag3 = new MyHashtag(u1, "배달의 민족");

		MyHashtag myHashtag4 = new MyHashtag(u2, "평일근무");
		MyHashtag myHashtag5 = new MyHashtag(u2, "아침형");
		MyHashtag myHashtag6 = new MyHashtag(u2, "배달의 민족");

		MyHashtag myHashtag7 = new MyHashtag(u3, "맛집러버");
		MyHashtag myHashtag8 = new MyHashtag(u3, "요리마스터");
		MyHashtag myHashtag9 = new MyHashtag(u3, "배달의 민족");
		MyHashtag myHashtag10 = new MyHashtag(u3, "아침형");

		MyHashtag myHashtag11 = new MyHashtag(u21, "집돌이/집순이");
		MyHashtag myHashtag12 = new MyHashtag(u21, "맛집러버");
		MyHashtag myHashtag13 = new MyHashtag(u21, "배달의 민족");

		MyHashtag myHashtag14 = new MyHashtag(u4, "활발한");



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
		em.persist(myHashtag13);
		em.persist(myHashtag14);

		//MyHomeHashtag 생성
		MyHomeHashtag mhh2_1 = new MyHomeHashtag("숲세권1", u2); MyHomeHashtag mhh2_2 = new MyHomeHashtag("역세권", u2);
		MyHomeHashtag mhh2_3 = new MyHomeHashtag("한강세권", u2); MyHomeHashtag mhh2_4 = new MyHomeHashtag("편세권", u2);
		MyHomeHashtag mhh2_5 = new MyHomeHashtag("야경맛집", u2); MyHomeHashtag mhh2_6 = new MyHomeHashtag("엘리베이터", u2);
		MyHomeHashtag mhh2_7 = new MyHomeHashtag("주차가능", u2); MyHomeHashtag mhh2_8 = new MyHomeHashtag("통창", u2);
		MyHomeHashtag mhh2_9 = new MyHomeHashtag("남향", u2); MyHomeHashtag mhh2_10 = new MyHomeHashtag("로켓와우", u2);
		MyHomeHashtag mhh2_11 = new MyHomeHashtag("고층", u2);

		MyHomeHashtag mhh3_1 = new MyHomeHashtag("숲세권", u3); MyHomeHashtag mhh3_2 = new MyHomeHashtag("역세권", u3);
		MyHomeHashtag mhh3_3 = new MyHomeHashtag("한강세권", u3); MyHomeHashtag mhh3_4 = new MyHomeHashtag("편세권", u3);
		MyHomeHashtag mhh3_5 = new MyHomeHashtag("야경맛집", u3); MyHomeHashtag mhh3_6 = new MyHomeHashtag("엘리베이터", u3);
		MyHomeHashtag mhh3_7 = new MyHomeHashtag("주차가능", u3); MyHomeHashtag mhh3_8 = new MyHomeHashtag("통창", u3);
		MyHomeHashtag mhh3_9 = new MyHomeHashtag("남향", u3); MyHomeHashtag mhh3_10 = new MyHomeHashtag("로켓와우", u3);
		MyHomeHashtag mhh3_11 = new MyHomeHashtag("고층", u3);

		MyHomeHashtag mhh4_1 = new MyHomeHashtag("숲세권", u4); MyHomeHashtag mhh4_2 = new MyHomeHashtag("역세권", u4);
		MyHomeHashtag mhh4_3 = new MyHomeHashtag("한강세권", u4); MyHomeHashtag mhh4_4 = new MyHomeHashtag("편세권", u4);
		MyHomeHashtag mhh4_5 = new MyHomeHashtag("야경맛집", u4); MyHomeHashtag mhh4_6 = new MyHomeHashtag("엘리베이터", u4);
		MyHomeHashtag mhh4_7 = new MyHomeHashtag("주차가능", u4); MyHomeHashtag mhh4_8 = new MyHomeHashtag("통창", u4);
		MyHomeHashtag mhh4_9 = new MyHomeHashtag("남향", u4); MyHomeHashtag mhh4_10 = new MyHomeHashtag("로켓와우", u4);
		MyHomeHashtag mhh4_11 = new MyHomeHashtag("고층", u4);

		MyHomeHashtag mhh5_1 = new MyHomeHashtag("숲세권", u5); MyHomeHashtag mhh5_2 = new MyHomeHashtag("역세권", u5);
		MyHomeHashtag mhh5_3 = new MyHomeHashtag("한강세권", u5); MyHomeHashtag mhh5_4 = new MyHomeHashtag("편세권", u5);
		MyHomeHashtag mhh5_5 = new MyHomeHashtag("야경맛집", u5); MyHomeHashtag mhh5_6 = new MyHomeHashtag("엘리베이터", u5);
		MyHomeHashtag mhh5_7 = new MyHomeHashtag("주차가능", u5); MyHomeHashtag mhh5_8 = new MyHomeHashtag("통창", u5);
		MyHomeHashtag mhh5_9 = new MyHomeHashtag("남향", u5); MyHomeHashtag mhh5_10 = new MyHomeHashtag("로켓와우", u5);
		MyHomeHashtag mhh5_11 = new MyHomeHashtag("고층", u5);

		MyHomeHashtag mhh6_1 = new MyHomeHashtag("숲세권", u6); MyHomeHashtag mhh6_2 = new MyHomeHashtag("역세권", u6);
		MyHomeHashtag mhh6_3 = new MyHomeHashtag("한강세권", u6); MyHomeHashtag mhh6_4 = new MyHomeHashtag("편세권", u6);
		MyHomeHashtag mhh6_5 = new MyHomeHashtag("야경맛집", u6); MyHomeHashtag mhh6_6 = new MyHomeHashtag("엘리베이터", u6);
		MyHomeHashtag mhh6_7 = new MyHomeHashtag("주차가능", u6); MyHomeHashtag mhh6_8 = new MyHomeHashtag("통창", u6);
		MyHomeHashtag mhh6_9 = new MyHomeHashtag("남향", u6); MyHomeHashtag mhh6_10 = new MyHomeHashtag("로켓와우", u6);
		MyHomeHashtag mhh6_11 = new MyHomeHashtag("고층", u6);

		MyHomeHashtag mhh7_1 = new MyHomeHashtag("숲세권", u7); MyHomeHashtag mhh7_2 = new MyHomeHashtag("역세권", u7);
		MyHomeHashtag mhh7_3 = new MyHomeHashtag("한강세권", u7); MyHomeHashtag mhh7_4 = new MyHomeHashtag("편세권", u7);
		MyHomeHashtag mhh7_5 = new MyHomeHashtag("야경맛집", u7); MyHomeHashtag mhh7_6 = new MyHomeHashtag("엘리베이터", u7);
		MyHomeHashtag mhh7_7 = new MyHomeHashtag("주차가능", u7); MyHomeHashtag mhh7_8 = new MyHomeHashtag("통창", u7);
		MyHomeHashtag mhh7_9 = new MyHomeHashtag("남향", u7); MyHomeHashtag mhh7_10 = new MyHomeHashtag("로켓와우", u7);
		MyHomeHashtag mhh7_11 = new MyHomeHashtag("고층", u7);

		em.persist(mhh2_1); em.persist(mhh2_2); em.persist(mhh2_3); em.persist(mhh2_4); em.persist(mhh2_5);
		em.persist(mhh2_6); em.persist(mhh2_7); em.persist(mhh2_8); em.persist(mhh2_9); em.persist(mhh2_10);  em.persist(mhh2_11);
		em.persist(mhh3_1); em.persist(mhh3_2); em.persist(mhh3_3); em.persist(mhh3_4); em.persist(mhh3_5);
		em.persist(mhh3_6); em.persist(mhh3_7); em.persist(mhh3_8); em.persist(mhh3_9); em.persist(mhh3_10);  em.persist(mhh3_11);
		em.persist(mhh4_1); em.persist(mhh4_2); em.persist(mhh4_3); em.persist(mhh4_4); em.persist(mhh4_5);
		em.persist(mhh4_6); em.persist(mhh4_7); em.persist(mhh4_8); em.persist(mhh4_9); em.persist(mhh4_10);  em.persist(mhh4_11);
		em.persist(mhh5_1); em.persist(mhh5_2); em.persist(mhh5_3); em.persist(mhh5_4); em.persist(mhh5_5);
		em.persist(mhh5_6); em.persist(mhh5_7); em.persist(mhh5_8); em.persist(mhh5_9); em.persist(mhh5_10);  em.persist(mhh5_11);
		em.persist(mhh6_1); em.persist(mhh6_2); em.persist(mhh6_3); em.persist(mhh6_4); em.persist(mhh6_5);
		em.persist(mhh6_6); em.persist(mhh6_7); em.persist(mhh6_8); em.persist(mhh6_9); em.persist(mhh6_10);  em.persist(mhh6_11);
		em.persist(mhh7_1); em.persist(mhh7_2); em.persist(mhh7_3); em.persist(mhh7_4); em.persist(mhh7_5);
		em.persist(mhh7_6); em.persist(mhh7_7); em.persist(mhh7_8); em.persist(mhh7_9); em.persist(mhh7_10);  em.persist(mhh7_11);

		//likeHashtag
		LikeHashtag lh1 = new LikeHashtag("배달의 민족", u1); LikeHashtag lh2 = new LikeHashtag("평일근무", u1);
		LikeHashtag lh3 = new LikeHashtag("아침형", u1); LikeHashtag lh4 = new LikeHashtag("맛집러버", u1);
		LikeHashtag lh5 = new LikeHashtag("요리마스터", u1); LikeHashtag lh6 = new LikeHashtag("집돌이/집순이", u1);
		LikeHashtag lh7 = new LikeHashtag("성실한", u1);
		em.persist(lh1); em.persist(lh2); em.persist(lh3); em.persist(lh4); em.persist(lh5);
		em.persist(lh6); em.persist(lh7);

		//likeHomeHashtag
		LikeHomeHashtag lhh1 = new LikeHomeHashtag("숲세권", u1); LikeHomeHashtag lhh2 = new LikeHomeHashtag("한강세권", u1);
		LikeHomeHashtag lhh3 = new LikeHomeHashtag("로켓와우", u1); LikeHomeHashtag lhh4 = new LikeHomeHashtag("엘리베이터", u1);
		LikeHomeHashtag lhh5 = new LikeHomeHashtag("베란다/발코니", u1); LikeHomeHashtag lhh6 = new LikeHomeHashtag("SSG배송", u1);
		LikeHomeHashtag lhh7 = new LikeHomeHashtag("야경맛집", u1); LikeHomeHashtag lhh8 = new LikeHomeHashtag("통창", u1);
		LikeHomeHashtag lhh9 = new LikeHomeHashtag("샛별배송", u1); LikeHomeHashtag lhh10 = new LikeHomeHashtag("역세권", u1);
		LikeHomeHashtag lhh11 = new LikeHomeHashtag("복층", u1);

		em.persist(lhh1); em.persist(lhh2); em.persist(lhh3); em.persist(lhh4); em.persist(lhh5);
		em.persist(lhh6); em.persist(lhh7); em.persist(lhh8); em.persist(lhh9); em.persist(lhh10); em.persist(lhh11);

		//Community 생성
		/*CommunityBoardDto c1 = new CommunityBoardDto("제목1(방자랑)", "방자랑1", 1);
		CommunityBoardDto c2 = new CommunityBoardDto("제목1(정보공유)", "정보공유1", 2);
		CommunityBoardDto c3 = new CommunityBoardDto("제목2(방자랑)", "방자랑2", 1);
		CommunityBoardDto c4 = new CommunityBoardDto("제목1(자유)", "자유1", 3);
		CommunityBoardDto c5 = new CommunityBoardDto("제목2(정보공유)", "정보공유2", 2);
		CommunityBoardDto c6 = new CommunityBoardDto("제목2(자유)", "자유2", 3);
		CommunityBoardDto c7 = new CommunityBoardDto("제목3(방자랑)", "방자랑3", 1);
		CommunityBoardDto c8 = new CommunityBoardDto("제목3(정보공유)", "정보공유3", 2);*/
        CommunityBoardDto c1 = new CommunityBoardDto("제목1(방자랑)", "방자랑1", 1);
		CommunityBoardDto c2 = new CommunityBoardDto("제목1(정보공유)", "정보공유1", 1);
		CommunityBoardDto c3 = new CommunityBoardDto("제목2(방자랑)", "방자랑2", 1);
		CommunityBoardDto c4 = new CommunityBoardDto("제목1(자유)", "자유1", 1);
		CommunityBoardDto c5 = new CommunityBoardDto("제목2(정보공유)", "정보공유2", 1);
		CommunityBoardDto c6 = new CommunityBoardDto("제목2(자유)", "자유2", 1);
		CommunityBoardDto c7 = new CommunityBoardDto("제목3(방자랑)", "방자랑3", 1);
		CommunityBoardDto c8 = new CommunityBoardDto("제목3(정보공유)", "정보공유3", 1);

		CommunityBoard communityBoard1 = new CommunityBoard(u1, c1);
		CommunityBoard communityBoard2 = new CommunityBoard(u1, c2);
        CommunityBoard communityBoard3 = new CommunityBoard(u2, c3);
        CommunityBoard communityBoard4 = new CommunityBoard(u2, c4);
		CommunityBoard communityBoard5 = new CommunityBoard(u3, c5);
		CommunityBoard communityBoard6 = new CommunityBoard(u3, c6);
		CommunityBoard communityBoard7 = new CommunityBoard(u21, c7);
		CommunityBoard communityBoard8 = new CommunityBoard(u21, c8);

        //강제 조회수 증가
        communityBoard1.updateViews(3);
        communityBoard2.updateViews(4);
        communityBoard3.updateViews(2);
        communityBoard4.updateViews(5);
        communityBoard5.updateViews(1);
        communityBoard6.updateViews(7);
        communityBoard7.updateViews(10);
        communityBoard8.updateViews(9);

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
