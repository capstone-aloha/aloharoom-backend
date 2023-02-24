package com.aloharoombackend.service;

import com.aloharoombackend.dto.SignUpDto;
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
    SignUpDto signUpDto = new SignUpDto();

    @Transactional
    public Long create(MyHashtag myHashtag) {
        myHashtagRepository.save(myHashtag);
        return myHashtag.getId();
    }

    public List<MyHashtag> findAll() {
        return myHashtagRepository.findAll();
    }
}
