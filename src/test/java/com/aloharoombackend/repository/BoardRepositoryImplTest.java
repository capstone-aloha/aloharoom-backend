package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAddDto;
import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;
import java.util.stream.Collectors;

import static com.aloharoombackend.model.QBoard.board;
import static com.aloharoombackend.model.QHome.home;
import static com.aloharoombackend.model.QMyHashtag.myHashtag;
import static com.aloharoombackend.model.QUser.user;

@SpringBootTest
@Transactional
class BoardRepositoryImplTest {
    //테스트 해봐야 in쿼리 작동하는지 알듯

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        //Home 생성
        BoardAddDto b1 = new BoardAddDto(); b1.setFlat(30); b1.setRoomCount(3); b1.setRent(25); b1.setHomeType("apartment");
        Home h1 = new Home(b1);
        BoardAddDto b2 = new BoardAddDto(); b2.setFlat(32); b2.setRoomCount(1); b2.setRent(25); b2.setHomeType("apartment");
        Home h2 = new Home(b2);
        BoardAddDto b3 = new BoardAddDto(); b3.setFlat(35); b3.setRoomCount(2); b3.setRent(25); b3.setHomeType("apartment");
        Home h3 = new Home(b3);
        BoardAddDto b4 = new BoardAddDto(); b4.setFlat(40); b4.setRoomCount(4); b4.setRent(25); b4.setHomeType("apartment");
        Home h4 = new Home(b4);

        em.persist(h1);
        em.persist(h2);
        em.persist(h3);
        em.persist(h4);

        //User 생성
        User u1 = new User(new SignUpDto("admin1", "1234", 20, "male"));
        User u2 = new User(new SignUpDto("admin2", "1234", 21, "female"));
        User u3 = new User(new SignUpDto("admin3", "1234", 22, "male"));
        User u4 = new User(new SignUpDto("admin4", "1234", 23, "male"));
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);

        //Board 생성
        Board board1 = new Board(h1, u1, new BoardAddDto());
        Board board2 = new Board(h2, u2, new BoardAddDto());
        Board board3 = new Board(h3, u3, new BoardAddDto());
        Board board4 = new Board(h4, u4, new BoardAddDto());
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

    @Test
    @Rollback(false)
    public void batchSizeTest() {
        //select Board 가 User수 만큼 나가는데 왜 나가는지 모르겠다 => User가 Board의 주인어어서 무조건 나가는 듯?
        List<User> users = queryFactory
                .selectFrom(user).distinct()
                .join(user.myHashtags)
                .leftJoin(user.board).fetchJoin() //이래야 사용자마다 select Board 쿼리 1번씩 안 나감
                .fetch();
        System.out.println("user수: " + users.size());

        //모든 유저의 해시태그 => 실 객체 변환 (In쿼리 1번 발생)
        users.stream().forEach(user1 -> user1.getLikeHashtags().stream().forEach(likeHashtag1 -> likeHashtag1.getHash()));
    }

    @Test
    @Rollback(false)
    public void searchFilter_test() {
        Set<String> loginUserTagSet = new HashSet<>();
        SearchFilterDto searchFilterDto = new SearchFilterDto(20, 23, "male",
                new ArrayList<>(Arrays.asList("조용한", "아침형", "배달의 민족")),
                30, 40, 25, 27, 3, "apartment");
        Integer minAge = searchFilterDto.getAgeRange().get(0);
        Integer maxAge = searchFilterDto.getAgeRange().get(1);
        Integer minFlat = searchFilterDto.getFlatRange().get(0);
        Integer maxFlat = searchFilterDto.getFlatRange().get(1);
        Integer minRent = searchFilterDto.getRentRange().get(0);
        Integer maxRent = searchFilterDto.getRentRange().get(1);
        //User 쿼리 (필터를 만족하는 사용자 추출)
        List<User> users = queryFactory
                .selectFrom(user).distinct()
                .join(user.myHashtags, myHashtag).fetchJoin()
                .leftJoin(user.board, board).fetchJoin() //이래야 사용자마다 select Board 쿼리 1번씩 안 나감
                .where(
                        board.isNotNull(),
                        user.age.goe(minAge), //user.age >= minAge
                        user.age.loe(maxAge), //user.age <= maxAge
                        user.gender.eq("male") //user.gender === gender
                )
                .fetch();
        System.out.println("나이, 성별 필터링 완료 후 users수: " + users.size()); //3
        //해시태그 개수로 필터링
        List<User> newUsers = new ArrayList<>();
        users.stream().forEach(user -> {
            if (user.getMyHashtags().size() == searchFilterDto.getLikeHashtags().size())
                newUsers.add(user);
        });
        System.out.println("해시태그 개수 필터링 완료 후 user수: " + newUsers.size()); //3
        //로그인 사용자 해시태그 Set에 저장
        List<String> loginUserTags = searchFilterDto.getLikeHashtags();
        loginUserTags.stream()
                .forEach(likeHashtag -> loginUserTagSet.add(likeHashtag));

        users.clear();
        //해시태그 필터링 => 이진탐색, 로그인 사용자 값 map 만들고 없으면 바로 짤
        for (User user : newUsers) {
            List<MyHashtag> myHashtags = user.getMyHashtags();
            boolean flag = false;
            for (MyHashtag myHashtag : myHashtags) {
                if(!loginUserTagSet.contains(myHashtag.getHash())) {
                    flag = true; break;
                }
            }
            //해시태그 + 가전제품 필터를 통과한 User만 저장
            if(!flag) users.add(user);
        }
        System.out.println("해시태그 필터링 완료 후 user수:: " + users.size());
        //userId값 추줄
        List<Long> userIds = users.stream()
                .map(user -> user.getId()).collect(Collectors.toList());


        //해시태그 필터링 완료된 사용자에 대한 집 필터링 쿼리
        List<Board> boards = queryFactory
                .selectFrom(board)
                .join(board.home, home).fetchJoin()
//                .join(home.homeImages).fetchJoin() => 현재 집사진이 없으니 빼야함.
                .join(board.user, user)
                .where(
                        user.id.in(userIds),
                        home.flat.goe(minFlat), //home.flat >= minFlat
                        home.flat.loe(maxFlat), //home.flat <= maxFlat
                        home.roomCount.eq(searchFilterDto.getRoomCount()),
                        home.rent.goe(minRent), //home.rent >= minRent
                        home.rent.loe(maxRent) //home.rent <= maxRent
                )
                .fetch();

        List<Long> boardIds = boards.stream().map(board -> board.getId()).collect(Collectors.toList());
        System.out.println("boardId 수: " + boardIds.size());
        for (Long boardId : boardIds) {
            System.out.println("boardId = " + boardId); //1만 뽑혀야 성공
        }
    }


    @Test
    @Rollback(false)
    public void searchFilter() {
        //실제 리포지토리로 테스트 => .join(home.homeImages).fetchJoin() 주석처리 후 테스트!
        //u2:성별로 필터링, u3: 해시태그로 필터링, u4: 방 개수로 필터링 => u1만 뽑힘.
        SearchFilterDto searchFilterDto = new SearchFilterDto(20, 23, "male",
                new ArrayList<>(Arrays.asList("조용한", "아침형", "배달의 민족")),
                30, 40, 25, 27, 3, "apartment");


        List<BoardAllDto> boardAllDtos = boardRepository.searchFilter(searchFilterDto);
        for (BoardAllDto boardAllDto : boardAllDtos) {
            System.out.println(boardAllDto);
        }
        BoardAllDto findBoardDto = boardAllDtos.get(0);
        Assertions.assertThat(findBoardDto.getBoardId()).isEqualTo(1L);
    }
}

