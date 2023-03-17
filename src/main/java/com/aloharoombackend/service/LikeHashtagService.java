package com.aloharoombackend.service;

import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.model.HomeImage;
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

    public List<LikeHashtag> findAll() {
        return likeHashtagRepository.findAll();
    }

    @Transactional
    public void create(List<LikeHashtag> likeHashtags) {
        likeHashtags.stream().forEach(likeProduct -> likeHashtagRepository.save(likeProduct));
    }

    @Transactional
    public void delete(LikeHashtag likeHashtag) {
        likeHashtagRepository.delete(likeHashtag);
    }

}
