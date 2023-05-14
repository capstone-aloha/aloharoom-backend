package com.aloharoombackend.service;

import com.aloharoombackend.model.MyHomeHashtag;
import com.aloharoombackend.repository.MyHomeHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyHomeHashtagService {

    private final MyHomeHashtagRepository myHomeHashtagRepository;

    public List<MyHomeHashtag> findAll() {
        return myHomeHashtagRepository.findAll();
    }

    @Transactional
    public void create(List<MyHomeHashtag> myHomeHashtags) {
        myHomeHashtags.stream().forEach(myHomeHashtag -> myHomeHashtagRepository.save(myHomeHashtag));
    }

    @Transactional
    public void delete(MyHomeHashtag myHomeHashtag) {
        myHomeHashtagRepository.delete(myHomeHashtag);
    }
}
