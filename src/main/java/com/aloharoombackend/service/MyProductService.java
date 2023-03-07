package com.aloharoombackend.service;

import com.aloharoombackend.model.LikeProduct;
import com.aloharoombackend.model.MyProduct;
import com.aloharoombackend.repository.MyProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyProductService {

    private final MyProductRepository myProductRepository;

    @Transactional
    public void create(List<MyProduct> myProducts) {
//        likeProducts.stream().map(likeProduct -> likeProductRepository.save(likeProduct));
        myProducts.stream().forEach(likeProduct -> myProductRepository.save(likeProduct));
    }

    public List<MyProduct> findAll() {
        return myProductRepository.findAll();
    }
}
