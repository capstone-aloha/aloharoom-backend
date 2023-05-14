package com.aloharoombackend.repository;

import com.aloharoombackend.model.MyHomeHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyHomeHashtagRepository extends JpaRepository<MyHomeHashtag, Long> {
}
