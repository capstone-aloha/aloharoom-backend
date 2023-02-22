package com.aloharoombackend.repository;

import com.aloharoombackend.model.MyHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyHashtagRepository extends JpaRepository<MyHashtag, Long> {
}
