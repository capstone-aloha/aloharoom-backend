package com.aloharoombackend.repository;

import com.aloharoombackend.dto.BoardAllDto;
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
        Map<String, Integer> mappingMap = new HashMap<>();
        Set<String> loginUserTagSet = new HashSet<>();
        //User 쿼리 (필터를 만족하는 사용자 추출)
        List<User> users = queryFactory
                .selectFrom(user)
                .join(user.myHashtags, myHashtag).fetchJoin()
                .leftJoin(user.board).fetchJoin() //이래야 사용자마다 select Board 쿼리 1번씩 안 나감
                .leftJoin(user.home)
                .where(
                        home.isNotNull(),
                        user.age.eq(searchFilterDto.getAge()),
                        user.gender.eq(searchFilterDto.getGender())
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
        List<LikeHashtag> loginUserTags = searchFilterDto.getLikeHashtags();
        loginUserTags.stream()
                        .forEach(likeHashtag -> loginUserTagSet.add(likeHashtag.getHash()));

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
                .join(home.homeImages).fetchJoin()
                .where(board.user.id.in(userIds),
                        home.flat.eq(searchFilterDto.getFlat()),
                        home.roomCount.eq(searchFilterDto.getRoomCount()),
                        home.homeType.eq(searchFilterDto.getHomeType()),
                        home.rent.eq(searchFilterDto.getRent())
                )
                .fetch();

        //board로 dto 만들어서 반환
        List<BoardAllDto> boardAllDtos = boards.stream().map(board -> new BoardAllDto(board, board.getHome()))
                .collect(Collectors.toList());

        return boardAllDtos;
    }
}
