package com.aloharoombackend.service;

import com.aloharoombackend.model.LikeHashtag;
import com.aloharoombackend.model.LikeProduct;
import com.aloharoombackend.repository.LikeHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeHashtagService {

    private final LikeHashtagRepository likeHashtagRepository;

    @Transactional
    public void create(List<LikeHashtag> likeHashtags) {
//        likeProducts.stream().map(likeProduct -> likeProductRepository.save(likeProduct));
        likeHashtags.stream().forEach(likeProduct -> likeHashtagRepository.save(likeProduct));
    }

    public List<LikeHashtag> findAll() {
        return likeHashtagRepository.findAll();
    }

}
