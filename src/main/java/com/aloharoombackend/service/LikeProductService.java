package com.aloharoombackend.service;

import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.model.LikeProduct;
import com.aloharoombackend.repository.LikeProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeProductService {

    private final LikeProductRepository likeProductRepository;

    @Transactional
    public void create(List<LikeProduct> likeProducts) {
//        likeProducts.stream().map(likeProduct -> likeProductRepository.save(likeProduct));
        likeProducts.stream().forEach(likeProduct -> likeProductRepository.save(likeProduct));
    }
//
//    @Transactional
//    public void test() {
//        likeProductRepository.save(new LikeProduct("as"));
//    }
}


