package com.aloharoombackend.service;

import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.LikeHashtag;
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

    public List<MyHashtag> findAll() {
        return myHashtagRepository.findAll();
    }

    @Transactional
    public void create(List<MyHashtag> myHashtags) {
        myHashtags.stream().forEach(likeProduct -> myHashtagRepository.save(likeProduct));
    }

    @Transactional
    public void delete(MyHashtag myHashtag) {
        myHashtagRepository.delete(myHashtag);
    }
}
