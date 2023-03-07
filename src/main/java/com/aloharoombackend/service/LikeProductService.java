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
    List<LikeProduct> likeProducts = new ArrayList<>();


    /*@Transactional
    public Long create(LikeProduct likeProduct) {
        likeProductRepository.save(likeProduct);
        return likeProduct.getId();
    }*/

    /*@Transactional
    public void create(List<SignUpDto> signUpDto) {
        //likeProductRepository.save(likeProducts);
        likeProducts = likeProduct
                .stream().map(likeProduct -> new LikeProduct(likeProduct)).collect(Collectors.toList());

    }*/

    @Transactional
    public void create(List<LikeProduct> signUpDto) {
        List<LikeProduct> likeProducts = signUpDto.stream()
                .map(SignUpDto::getLikeProducts)
                .collect(Collectors.toList());
        likeProductRepository.saveAll(likeProducts);
    }

    public List<LikeProduct> findAll() {
        return likeProductRepository.findAll();
    }
}
