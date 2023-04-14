package com.aloharoombackend.repository;

import com.aloharoombackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
