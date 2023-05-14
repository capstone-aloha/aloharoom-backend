package com.aloharoombackend.service;

import com.aloharoombackend.model.LikeHomeHashtag;
import com.aloharoombackend.repository.LikeHomeHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeHomeHashtagService {

    private final LikeHomeHashtagRepository likeHomeHashtagRepository;

    public List<LikeHomeHashtag> findAll() {
        return likeHomeHashtagRepository.findAll();
    }

    @Transactional
    public void create(List<LikeHomeHashtag> likeHomeHashtags) {
        likeHomeHashtags.stream().forEach(likeHomeHashtag -> likeHomeHashtagRepository.save(likeHomeHashtag));
    }

    @Transactional
    public void delete(LikeHomeHashtag likeHomeHashtag) {
        likeHomeHashtagRepository.delete(likeHomeHashtag);
    }

}


