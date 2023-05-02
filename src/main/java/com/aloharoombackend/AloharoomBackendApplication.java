package com.aloharoombackend;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.CommunityAllDto;
import com.aloharoombackend.dto.CommunityBoardDto;
import com.aloharoombackend.dto.SignUpDto;
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
		BoardAddDto b1 = new BoardAddDto("방 게시물1 내용입니다.", 3, "서울특별시 성북구 삼선교로16길 116", "apartment", "매매", 2000,
				10, 25, 30, 15, 8, 20, LocalDate.now(), 37.582812, 127.010233, Arrays.asList(new Integer[]{20, 25}));
		Home h1 = new Home(b1);
		BoardAddDto b2 = new BoardAddDto("방 게시물2 내용입니다.", 3, "서울특별시 영등포구 당산동5가 42", "apartment", "매매", 2000,
				10, 30, 38, 15, 2, 25, LocalDate.now(), 37.531854, 126.902958, Arrays.asList(new Integer[]{22, 25}));
		Home h2 = new Home(b2);
		BoardAddDto b3 = new BoardAddDto("방 게시물3 내용입니다.", 3, "서울특별시 강동구 강동구 고덕로390", "apartment", "매매", 2000,
				10, 29, 20, 15, 2, 25, LocalDate.now(), 37.555031, 127.168604, Arrays.asList(new Integer[]{26, 28}));
		Home h3 = new Home(b3);
		BoardAddDto b4 = new BoardAddDto("방 게시물4 내용입니다.", 3, "서울특별시 강서구 공항대로 247", "apartment", "매매", 2000,
				10, 40, 34, 15, 2, 25, LocalDate.now(), 37.559246, 126.834986, Arrays.asList(new Integer[]{29, 29}));
		Home h4 = new Home(b4);

		//방 사진 등록
		List<String> imgUrls = new ArrayList<>(
				List.of(new String[]{"71558773-1fb0-41eb-af08-2f54efa3de23.jpg", "887b846d-d269-4012-a456-15b5d5c553b5.jpg",
						"965242e2-a5b4-4e00-997c-9a3449eac583.jpg", "4eec3956-b0dc-4815-8861-57839d3574e9.jpg"}));
		imgUrls.stream().map(imgUrl -> new HomeImage(h1, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h2, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h3, imgUrl)).collect(Collectors.toList());
		imgUrls.stream().map(imgUrl -> new HomeImage(h4, imgUrl)).collect(Collectors.toList());

		em.persist(h1);
		em.persist(h2);
		em.persist(h3);
		em.persist(h4);

		//User 생성
		String encodePw = bCryptPasswordEncoder.encode("1234");
		SignUpDto su1 = new SignUpDto("admin1@naver.com", encodePw, 20, "male");
		su1.setNickname("admin1"); User u1 = new User(su1); u1.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/2ae4e876-3ee4-49a1-a1e9-c197534e9445.png");
		SignUpDto su2 = new SignUpDto("admin2@naver.com", encodePw, 21, "female");
		su2.setNickname("admin2"); User u2 = new User(su2); u2.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/2ae4e876-3ee4-49a1-a1e9-c197534e9445.png");
		SignUpDto su3 = new SignUpDto("admin3@naver.com", encodePw, 22, "male");
		su3.setNickname("admin3"); User u3 = new User(su3); u3.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/2ae4e876-3ee4-49a1-a1e9-c197534e9445.png");
		SignUpDto su4 = new SignUpDto("admin4@naver.com", encodePw, 23, "male");
		su4.setNickname("admin4"); User u4 = new User(su4); u4.setProfileUrl("https://test-aloha1.s3.ap-northeast-2.amazonaws.com/2ae4e876-3ee4-49a1-a1e9-c197534e9445.png");
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
		CommunityBoard communityBoard7 = new CommunityBoard(u4, c7);
		CommunityBoard communityBoard8 = new CommunityBoard(u4, c8);

		//커뮤니티 이미지(방 이미지와 동일하게 해둠)
		List<String> CommunityImgUrls = new ArrayList<>(
				List.of(new String[]{"33.jpg", "71558773-1fb0-41eb-af08-2f54efa3de23.jpg", "887b846d-d269-4012-a456-15b5d5c553b5.jpg",
						"965242e2-a5b4-4e00-997c-9a3449eac583.jpg", "4eec3956-b0dc-4815-8861-57839d3574e9.jpg"}));
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

		em.flush(); em.clear();
	}
}
