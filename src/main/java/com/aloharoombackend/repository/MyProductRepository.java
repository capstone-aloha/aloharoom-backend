package com.aloharoombackend.repository;

import com.aloharoombackend.model.MyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyProductRepository extends JpaRepository<MyProduct, Long> {
}
