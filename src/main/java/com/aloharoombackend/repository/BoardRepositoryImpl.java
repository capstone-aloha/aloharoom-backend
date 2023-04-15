package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.model.*;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.aloharoombackend.model.QBoard.*;
import static com.aloharoombackend.model.QHome.home;
import static com.aloharoombackend.model.QMyHashtag.myHashtag;
import static com.aloharoombackend.model.QUser.user;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BoardAllDto> searchFilter(SearchFilterDto searchFilterDto) {
        Set<String> loginUserTagSet = new HashSet<>();
        //User 쿼리 (필터를 만족하는 사용자 추출)
        List<User> users = queryFactory
                .selectFrom(user).distinct()
                .join(user.myHashtags, myHashtag).fetchJoin()
                .leftJoin(user.board, board).fetchJoin() //이래야 사용자마다 select Board 쿼리 1번씩 안 나감
                .where(
                        board.isNotNull(),
                        user.age.goe(searchFilterDto.getMinAge()), //user.age >= minAge
                        user.age.loe(searchFilterDto.getMaxAge()), //user.age <= maxAge
                        user.gender.eq(searchFilterDto.getGender()) //user.gender == gender
                )
                .fetch();
        //myHashtag 변환말고 가전제품 변환 해야함. 나중에 바꿔
        users.stream().forEach(user -> user.getMyHashtags().stream().forEach(myHashtag -> myHashtag.getHash()));
        //집이 있는 사람 완료 여기서 해시태그로 필터링 후에 그 뭐냐 userId값만 뽑아서 게시물 뽑아야 할듯

        //해시태그 개수로 필터링
        List<User> newUsers = new ArrayList<>();
        users.stream().forEach(user -> {
            if (user.getMyHashtags().size() == searchFilterDto.getLikeHashtags().size())
                newUsers.add(user);
        });

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

        //userId값 추줄
        List<Long> userIds = users.stream()
                .map(user -> user.getId()).collect(Collectors.toList());

        //해시태그 필터링 완료된 사용자에 대한 집 필터링 쿼리
        List<Board> boards = queryFactory
                .selectFrom(board)
                .join(board.home, home).fetchJoin()
                .join(home.homeImages).fetchJoin() //테스트 코드에 이미지 설정 X => 테스트 시 주석처리 후 사용해야함.
                .join(board.user, user)
                .where(
                        user.id.in(userIds),
                        home.flat.goe(searchFilterDto.getMinFlat()), //home.flat >= minFlat
                        home.flat.loe(searchFilterDto.getMaxFlat()), //home.flat <= maxFlat
                        home.roomCount.eq(searchFilterDto.getRoomCount()), //home.roomCount === roomCount
                        home.homeType.eq(searchFilterDto.getHomeType()), //home.homeType === homeType
                        home.rent.goe(searchFilterDto.getMinRent()), //home.rent >= minRent
                        home.rent.loe(searchFilterDto.getMaxRent()) //home.rent <= maxRent
                )
                .fetch();

        //board로 dto 만들어서 반환
        List<BoardAllDto> boardAllDtos = boards.stream().map(board -> new BoardAllDto(board, board.getHome()))
                .collect(Collectors.toList());

        return boardAllDtos;
    }

    @Override
    public List<HeartBoardDto> recentViewBoard(List<Long> boardIds) {
        List<Board> boards = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.home, home).fetchJoin()
                .leftJoin(home.homeImages).fetchJoin()
                .where(board.id.in(boardIds)).fetch();

        return boards.stream().map(board1 -> new HeartBoardDto(board1)).collect(Collectors.toList());
    }
}
