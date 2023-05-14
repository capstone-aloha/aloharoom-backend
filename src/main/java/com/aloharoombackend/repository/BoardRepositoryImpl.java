package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
import com.aloharoombackend.dto.HeartBoardDto;
import com.aloharoombackend.dto.RangeDto;
import com.aloharoombackend.dto.SearchFilterDto;
import com.aloharoombackend.model.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.aloharoombackend.model.QBoard.*;
import static com.aloharoombackend.model.QHome.home;
import static com.aloharoombackend.model.QHomeImage.*;
import static com.aloharoombackend.model.QMyHashtag.myHashtag;
import static com.aloharoombackend.model.QUser.user;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Board> findAllByRange(RangeDto rangeDto) {
        return  queryFactory
                .selectFrom(board).distinct()
                .join(board.user, user).fetchJoin()
                .join(board.home, home).fetchJoin()
                .join(home.homeImages, homeImage).fetchJoin()
                .where(
                        board.activation.eq(true),
                        home.x.between(rangeDto.getSouthWestLatitude(), rangeDto.getNorthEastLatitude()),
                        home.y.between(rangeDto.getSouthWestLongitude(), rangeDto.getNorthEastLongitude())
                )
                .fetch();
    }

    @Override
    public List<BoardAllDto> searchFilter(RangeDto rangeDto, SearchFilterDto searchFilterDto) {
        Integer minAge = searchFilterDto.getAgeRange().get(0);
        Integer maxAge = searchFilterDto.getAgeRange().get(1);
        Integer minFlat = searchFilterDto.getFlatRange().get(0);
        Integer maxFlat = searchFilterDto.getFlatRange().get(1);
        Integer minRent = searchFilterDto.getRentRange().get(0);
        Integer maxRent = searchFilterDto.getRentRange().get(1);
        Integer roomCount = searchFilterDto.getRoomCount();
        String homeType = searchFilterDto.getHomeType();
        String gender = searchFilterDto.getGender();

        //User 쿼리 (필터를 만족하는 사용자 추출)
        List<User> users = queryFactory
                .selectFrom(user).distinct()
                .join(user.myHashtags, myHashtag).fetchJoin()
                .leftJoin(user.board, board).fetchJoin() //이래야 사용자마다 select Board 쿼리 1번씩 안 나감
                .where(
                        board.isNotNull(),
                        user.age.goe(minAge), //user.age >= minAge
                        user.age.loe(maxAge), //user.age <= maxAge
                        eqGender(gender) //user.gender == gender
                )
                .fetch();
        users.stream().forEach(user -> user.getMyHomeHashtags().stream()
                .forEach(myHomeHashtag -> myHomeHashtag.getId())); //실객체 변환

        System.out.println("=====users개수===== " + users.size());

        List<User> hashtagPassUsers = new ArrayList<>();
        Set<String> userLikeHashtagSet = new HashSet<>();
        Set<String> userLikeHomeHashtagSet = new HashSet<>();
        List<String> selectHashtags = searchFilterDto.getLikeHashtags(); //필터링 모달에서 고른 사람 해시태그
        List<String> selectHomeHashtags = searchFilterDto.getLikeHomeHashtags(); //필터링 모달에서 고른 집 해시태그

        //해시태그 필터링
        for (User user : users) {
            List<MyHashtag> myHashtags = user.getMyHashtags();
            List<MyHomeHashtag> myHomeHashtags = user.getMyHomeHashtags();
            myHashtags.stream()
                    .forEach(myHashtag -> userLikeHashtagSet.add(myHashtag.getHash()));
            myHomeHashtags.stream()
                    .forEach(myHomeHashtag -> userLikeHomeHashtagSet.add(myHomeHashtag.getName()));
            boolean flag = false;
            for(String selectHashtag : selectHashtags) {
                if(!userLikeHashtagSet.contains(selectHashtag)) {
                    flag = true; break;
                }
            }
            if(flag) {
                userLikeHashtagSet.clear();
                userLikeHomeHashtagSet.clear();
                continue;
            }
            for(String selectHomeHashtag : selectHomeHashtags) {
                if(!userLikeHomeHashtagSet.contains(selectHomeHashtag)) {
                    flag = true; break;
                }
            }
            //내 해시태그 + 집 해시태그 필터를 통과한 User만 저장
            if(!flag) hashtagPassUsers.add(user);
            userLikeHashtagSet.clear();
            userLikeHomeHashtagSet.clear();
        }
        System.out.println("=====hashtagPassUsers개수===== " + hashtagPassUsers.size());

        //userId값 추줄
        List<Long> userIds = hashtagPassUsers.stream()
                .map(user -> user.getId()).collect(Collectors.toList());
        System.out.println("=====userIds개수===== " + userIds.size());

        //해시태그 필터링 완료된 사용자에 대한 집 필터링 쿼리
        List<Board> boards = queryFactory
                .selectFrom(board).distinct()
                .join(board.home, home).fetchJoin()
                .join(home.homeImages).fetchJoin() //테스트 코드에 이미지 설정 X => 테스트 시 주석처리 후 사용해야함.
                .join(board.user, user)
                .where(
                        user.id.in(userIds),
                        board.activation.eq(true),
                        home.x.between(rangeDto.getSouthWestLatitude(), rangeDto.getNorthEastLatitude()),
                        home.y.between(rangeDto.getSouthWestLongitude(), rangeDto.getNorthEastLongitude()),
                        home.flat.goe(minFlat), //home.flat >= minFlat
                        home.flat.loe(maxFlat), //home.flat <= maxFlat
                        eqRoomCount(roomCount), //home.roomCount === roomCount
                        eqHomeType(homeType), //home.homeType === homeType
                        home.rent.goe(minRent), //home.rent >= minRent
                        home.rent.loe(maxRent) //home.rent <= maxRent
                )
                .fetch();
        System.out.println("=====boards개수===== " + boards.size());
        //board로 dto 만들어서 반환
        List<BoardAllDto> boardAllDtos = boards.stream().map(board -> new BoardAllDto(board, board.getHome()))
                .collect(Collectors.toList());
        System.out.println("=====boardAllDtos개수===== " + boardAllDtos.size());
        return boardAllDtos;
    }
    public BooleanExpression eqGender(String gender) {
        if(gender.equals("male")) return user.gender.eq("male");
        else if(gender.equals("female")) return user.gender.eq("female");
        else return user.gender.in("male", "female");
    }

    public BooleanExpression eqRoomCount(Integer roomCount) {
        if(roomCount == 1) return home.roomCount.eq(1);
        else if(roomCount == 2) return home.roomCount.eq(2);
        else if(roomCount == 3) return home.roomCount.eq(3);
        else return home.roomCount.in(1, 2, 3);
    }

    public BooleanExpression eqHomeType(String homeType) {
        if(homeType.equals("officetel")) return home.homeType.eq("officetel");
        else if(homeType.equals("villa")) return home.homeType.eq("villa");
        else if(homeType.equals("apartment")) return home.homeType.eq("apartment");
        else return home.homeType.in("officetel", "villa", "apartment");
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
