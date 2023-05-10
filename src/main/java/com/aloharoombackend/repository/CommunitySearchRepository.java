package com.aloharoombackend.repository;

import com.aloharoombackend.model.CommunityBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommunitySearchRepository {

    private final EntityManager em;

    public List<CommunityBoard> searchByNickNameAndCode(String nickname, Integer code) {

        String jpql = "select c from CommunityBoard c where c.user.nickname = :nickname and c.code = :code";
        List<CommunityBoard> list = em.createQuery(jpql, CommunityBoard.class)
                .setParameter("nickname", nickname)
                .setParameter("code", code)
                .getResultList();
        return list;
    }
}