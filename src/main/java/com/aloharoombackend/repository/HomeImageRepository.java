package com.aloharoombackend.repository;

import com.aloharoombackend.model.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeImageRepository extends JpaRepository<HomeImage, Long> {
}
