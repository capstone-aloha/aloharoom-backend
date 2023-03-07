package com.aloharoombackend.service;

import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.LikeProduct;
import com.aloharoombackend.model.MyHashtag;
import com.aloharoombackend.repository.MyHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyHashtagService {

    private final MyHashtagRepository myHashtagRepository;

    @Transactional
    public void create(List<MyHashtag> myHashtags) {
//        likeProducts.stream().map(likeProduct -> likeProductRepository.save(likeProduct));
        myHashtags.stream().forEach(likeProduct -> myHashtagRepository.save(likeProduct));
    }

    public List<MyHashtag> findAll() {
        return myHashtagRepository.findAll();
    }
}
