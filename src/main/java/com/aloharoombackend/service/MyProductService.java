package com.aloharoombackend.service;

import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.model.LikeProduct;
import com.aloharoombackend.model.MyHashtag;
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

    public List<MyProduct> findAll() {
        return myProductRepository.findAll();
    }

    @Transactional
    public void create(List<MyProduct> myProducts) {
        myProducts.stream().forEach(myProduct -> myProductRepository.save(myProduct));
    }

    @Transactional
    public void delete(MyProduct myProduct) {
        myProductRepository.delete(myProduct);
    }
}
