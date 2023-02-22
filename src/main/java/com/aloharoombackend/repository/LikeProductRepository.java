package com.aloharoombackend.repository;

import com.aloharoombackend.model.LikeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {

}
