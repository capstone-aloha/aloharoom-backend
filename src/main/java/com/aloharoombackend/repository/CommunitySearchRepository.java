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

    public List<CommunityBoard> searchByNickName(String nickname) {

        String jpql = "select c from CommunityBoard c where c.user.nickname = :nickname";
        List<CommunityBoard> list = em.createQuery(jpql, CommunityBoard.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return list;
    }
}