package com.aloharoombackend.service;

import com.aloharoombackend.model.LikeHashtag;
import com.aloharoombackend.model.LikeProduct;
import com.aloharoombackend.repository.LikeProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeProductService {

    private final LikeProductRepository likeProductRepository;
    public List<LikeProduct> findAll() {
        return likeProductRepository.findAll();
    }
}
